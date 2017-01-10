package com.scott.lib.imageloader;

import android.content.Context;

import com.scott.lib.imageloader.glide.GlideImageLoaderStrategy;
import com.scott.lib.imageloader.glide.glideprogress.ProgressLoadListener;

/**
 * author: heshantao
 * data: 2017/1/10.
 */

public class ImageLoaderUtil {

    public static final int PIC_LARGE = 0;
    public static final int PIC_MEDIUM = 1;
    public static final int PIC_SMALL = 2;

    public static final int LOAD_STRATEGY_NORMAL = 0;
    public static final int LOAD_STRATEGY_ONLY_WIFI = 1;

    private BaseImageLoaderStrategy mStrategy;

    public ImageLoaderUtil() {
        mStrategy = new GlideImageLoaderStrategy();
    }

    public void loadImage(Context context, ImageLoaderConfiguration configuration) {
        mStrategy.loadImage(context, configuration);
    }

    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        mStrategy = strategy;
    }


    public void loadGifImage(Context context, ImageLoaderConfiguration configuration) {
        mStrategy.loadGifImage(context,configuration);
    }




    public void loadImageWithProgress(Context context, ImageLoaderConfiguration configuration, ProgressLoadListener listener) {
        mStrategy.loadImageWithProgress(context,configuration,listener);
    }

    public void loadGifWithPrepareCall(Context context, ImageLoaderConfiguration configuration, SourceReadyListener listener) {
        mStrategy.loadGifWithPrepareCall(context,configuration,listener);
    }


    /**
     * 清除图片磁盘缓存
     */
    public void clearImageDiskCache(final Context context) {
        mStrategy.clearImageDiskCache(context);
    }

    /**
     * 清除图片内存缓存
     */
    public void clearImageMemoryCache(Context context) {
        mStrategy.clearImageMemoryCache(context);
    }

    /**
     * 根据不同的内存状态，来响应不同的内存释放策略
     *
     * @param context
     * @param level
     */
    public void trimMemory(Context context, int level) {
        mStrategy.trimMemory(context, level);
    }

    /**
     * 清除图片所有缓存
     */
    public void clearImageAllCache(Context context) {
        clearImageDiskCache(context.getApplicationContext());
        clearImageMemoryCache(context.getApplicationContext());
    }

    /**
     * 获取缓存大小
     *
     * @return CacheSize
     */
    public String getCacheSize(Context context) {
        return mStrategy.getCacheSize(context);
    }

    public void saveImage(Context context, String url, String savePath, String saveFileName, ImageSaveListener listener) {
        mStrategy.saveImage(context, url, savePath, saveFileName, listener);
    }
}
