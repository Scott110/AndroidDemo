package com.scott.lib.imageloader;

import android.content.Context;

import com.scott.lib.imageloader.glide.glideprogress.ProgressLoadListener;

/**
 * author: heshantao
 * data: 2017/1/10.
 * 图片加载策略
 */

public interface BaseImageLoaderStrategy {

    void loadImage(Context context, ImageLoaderConfiguration configuration);

    void loadGifImage(Context context, ImageLoaderConfiguration configuration);

    //加载图片时候获取图片的进度条
    void loadImageWithProgress(Context context, ImageLoaderConfiguration configuration,  ProgressLoadListener listener);

    void loadGifWithPrepareCall(Context context, ImageLoaderConfiguration configuration, SourceReadyListener listener);

    //清除硬盘缓存
    void clearImageDiskCache(final Context context);

    //清除内存缓存
    void clearImageMemoryCache(Context context);

    //根据不同的内存状态，来响应不同的内存释放策略
    void trimMemory(Context context, int level);

    //获取缓存大小
    String getCacheSize(Context context);

    //保存图片
    void saveImage(Context context, String url, String savePath, String saveFileName, ImageSaveListener listener);

}

