package com.scott.lib.imageloader.glide;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Looper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.scott.lib.imageloader.BaseImageLoaderStrategy;
import com.scott.lib.config.ImageLoaderConfiguration;
import com.scott.lib.imageloader.ImageLoaderUtil;
import com.scott.lib.imageloader.ImageSaveListener;
import com.scott.lib.imageloader.SourceReadyListener;
import com.scott.lib.imageloader.glide.glideprogress.ProgressLoadListener;
import com.scott.lib.imageloader.glide.glideprogress.ProgressModelLoader;
import com.scott.lib.imageloader.glide.glideprogress.ProgressUIListener;
import com.scott.util.CacheUtils;
import com.scott.util.FileUtils;
import com.scott.util.NetworkUtils;
import com.scott.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * author: heshantao
 * data: 2017/1/10.
 * glide 框架图片加载策略
 */
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {

    @Override
    public void loadImage(Context context, ImageLoaderConfiguration configuration) {
        int strategy = configuration.getLoadStrategy();
        if (strategy == ImageLoaderUtil.LOAD_STRATEGY_ONLY_WIFI) {
            int netType = NetworkUtils.getNetWorkType(context);
            //if wifi ,load pic
            if (netType == NetworkUtils.NETWORK_WIFI) {
                loadNormalImage(context, configuration);
            } else {
                //if not wifi ,load cache
                loadCacheImage(context, configuration);
            }
        } else {
            //如果不是在wifi下才加载图片
            loadNormalImage(context, configuration);
        }

    }


    /**
     * load image with Glide
     */
    private void loadNormalImage(Context ctx, ImageLoaderConfiguration configuration) {
        Glide.with(ctx).load(configuration.getUrl())
                .dontAnimate()
                .placeholder(configuration.getPlaceHolder())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).into(configuration.getImgView());
    }


    /**
     * load cache image with Glide
     */
    private void loadCacheImage(Context ctx, ImageLoaderConfiguration configuration) {
        Glide.with(ctx).using(new StreamModelLoader<String>() {
            @Override
            public DataFetcher<InputStream> getResourceFetcher(final String model, int width, int height) {
                return new DataFetcher<InputStream>() {
                    @Override
                    public InputStream loadData(Priority priority) throws Exception {
                        throw new IOException();
                    }

                    @Override
                    public void cleanup() {

                    }

                    @Override
                    public String getId() {
                        return model;
                    }

                    @Override
                    public void cancel() {

                    }
                };
            }
        }).load(configuration.getUrl())
                .dontAnimate().
                placeholder(configuration.getPlaceHolder())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(configuration.getImgView());
    }


    @Override
    public void loadGifImage(Context context, ImageLoaderConfiguration configuration) {
        int strategy = configuration.getLoadStrategy();
        if (strategy == ImageLoaderUtil.LOAD_STRATEGY_ONLY_WIFI) {
            int netType = NetworkUtils.getNetWorkType(context);
            //if wifi ,load pic
            if (netType == NetworkUtils.NETWORK_WIFI) {
                loadNormalGif(context, configuration);
            } else {
                //if not wifi ,load cache
                loadCacheGif(context, configuration);
            }
        } else {
            //如果不是在wifi下才加载gif图片
            loadNormalGif(context, configuration);
        }

    }


    private void loadNormalGif(Context ctx, ImageLoaderConfiguration configuration) {
        Glide.with(ctx).load(configuration.getUrl()).asGif().dontAnimate()
                .placeholder(configuration.getPlaceHolder())
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GifDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, String model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                }).into(configuration.getImgView());

    }


    private void loadCacheGif(Context ctx, ImageLoaderConfiguration configuration) {
        Glide.with(ctx).using(new StreamModelLoader<String>() {
            @Override
            public DataFetcher<InputStream> getResourceFetcher(final String model, int width, int height) {
                return new DataFetcher<InputStream>() {
                    @Override
                    public InputStream loadData(Priority priority) throws Exception {
                        throw new IOException();
                    }

                    @Override
                    public void cleanup() {

                    }

                    @Override
                    public String getId() {
                        return model;
                    }

                    @Override
                    public void cancel() {

                    }
                };
            }
        }).load(configuration.getUrl())
                .asGif()
                .dontAnimate()
                .skipMemoryCache(true)
                .placeholder(configuration.getPlaceHolder())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(configuration.getImgView());
    }

    @Override
    public void loadImageWithProgress(Context context, final ImageLoaderConfiguration configuration, final ProgressLoadListener listener) {
        Glide.with(context).using(new ProgressModelLoader(new ProgressUIListener() {
            @Override
            public void update(final int bytesRead, final int contentLength) {
                configuration.getImgView().post(new Runnable() {
                    @Override
                    public void run() {
                        listener.update(bytesRead, contentLength);
                    }
                });
            }
        })).load(configuration.getUrl())
                .skipMemoryCache(true)
                .dontAnimate().diskCacheStrategy(DiskCacheStrategy.SOURCE).
                listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        listener.onException();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        listener.onResourceReady();
                        return false;
                    }
                }).into(configuration.getImgView());
    }

    @Override
    public void loadGifWithPrepareCall(Context context, ImageLoaderConfiguration configuration, final SourceReadyListener listener) {
        Glide.with(context).load(configuration.getUrl()).asGif().dontAnimate()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).
                listener(new RequestListener<String, GifDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, String model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        listener.onResourceReady(resource.getIntrinsicWidth(), resource.getIntrinsicHeight());
                        return false;
                    }
                });
    }

    @Override
    public void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context.getApplicationContext()).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context.getApplicationContext()).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context.getApplicationContext()).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void trimMemory(Context context, int level) {
        Glide.get(context).trimMemory(level);
    }

    @Override
    public String getCacheSize(Context context) {
        try {
            return FileUtils.getFormatSize(FileUtils.getFolderSize(Glide.getPhotoCacheDir(context.getApplicationContext())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void saveImage(Context context, String url, String savePath, String saveFileName, ImageSaveListener listener) {
        if (!CacheUtils.isSDCardExsit() || StringUtils.isEmpty(url)) {
            listener.onSaveFail();
            return;
        }
        InputStream fromStream = null;
        OutputStream toStream = null;
        try {
            File cacheFile = Glide.with(context).load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
            if (cacheFile == null || !cacheFile.exists()) {
                listener.onSaveFail();
                return;
            }
            File dir = new File(savePath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, saveFileName + getPicType(cacheFile.getAbsolutePath()));

            fromStream = new FileInputStream(cacheFile);
            toStream = new FileOutputStream(file);
            byte length[] = new byte[1024];
            int count;
            while ((count = fromStream.read(length)) > 0) {
                toStream.write(length, 0, count);
            }
            //用广播通知相册进行更新相册
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);
            listener.onSaveSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            listener.onSaveFail();
        } finally {
            if (fromStream != null) {
                try {
                    fromStream.close();
                    toStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    fromStream = null;
                    toStream = null;
                }
            }
        }
    }


    public static String getPicType(String pathName) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        String type = options.outMimeType;
        if (!StringUtils.isEmpty(type)) {
            try {
                type = type.substring(6, type.length());
                if ("gif".equals(type)) {
                    return ".gif";
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return ".jpg";
    }


}
