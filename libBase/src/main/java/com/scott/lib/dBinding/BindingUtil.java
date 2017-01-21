package com.scott.lib.dBinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.scott.lib.imageloader.ImageLoaderUtil;
import com.scott.lib.manager.ImageLoaderConfigurationManager;

/**
 * author: heshantao
 * data: 2017/1/21.
 * dataBinding绑定属性
 */

public class BindingUtil {
    private static final String TAG = BindingUtil.class.getSimpleName();

    /*
    * 网络图片设置
    * xml 文件格式为
    *app:imageUrl="@{}
    * */
    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"bind:imageUrl"}, requireAll = false)
    public static void setImageView(ImageView imageView, String url) {
        ImageLoaderUtil loaderUtil = new ImageLoaderUtil();
        loaderUtil.loadImage(imageView.getContext(),
                ImageLoaderConfigurationManager.getInstance().DEFAULT(url, imageView));
    }

}
