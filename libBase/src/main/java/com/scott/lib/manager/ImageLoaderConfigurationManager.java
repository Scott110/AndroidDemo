package com.scott.lib.manager;

import android.widget.ImageView;

import com.scott.lib.config.ImageLoaderConfiguration;

/**
 * author: heshantao
 * data: 2017/1/21.
 * ImageLoader配置管理类
 */

public class ImageLoaderConfigurationManager {
    private static final String TAG = ImageLoaderConfigurationManager.class.getSimpleName();
    private volatile static ImageLoaderConfigurationManager INSTANCE;

    //构造方法私有
    private ImageLoaderConfigurationManager() {
    }

    //获取单例
    public static ImageLoaderConfigurationManager getInstance() {
        if (INSTANCE == null) {
            synchronized (ImageLoaderConfigurationManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ImageLoaderConfigurationManager();
                }
            }
        }
        return INSTANCE;
    }

    //获得默认图片加载配置信息
    public ImageLoaderConfiguration DEFAULT(String url, ImageView imageView) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder()
                .imgView(imageView)
                .url(url)
                .build();
        return config;
    }

}
