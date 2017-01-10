package com.scott.lib.imageloader;

import android.widget.ImageView;

import com.scott.lib.R;

/**
 * author: heshantao
 * data: 2017/1/10.
 * 图片加载配置
 */

public class ImageLoaderConfiguration {
    private int type;  //图片加载类型(big,medium,small)

    private String url; //需要解析的url

    private int placeHolder; //当没有成功加载的时候显示的图片

    private ImageView imgView; //ImageView的实例

    private int loadStrategy;//加载策略，目前只有默认加载策略，以后可以扩展

    public ImageLoaderConfiguration(Builder builder) {
        this.type = builder.type;
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.imgView = builder.imgView;
        this.loadStrategy = builder.loadStrategy;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }

    public int getLoadStrategy() {
        return loadStrategy;
    }

    public void setLoadStrategy(int loadStrategy) {
        this.loadStrategy = loadStrategy;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(int placeHolder) {
        this.placeHolder = placeHolder;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class Builder {
        private int type;
        private String url;
        private int placeHolder;
        private ImageView imgView;
        private int loadStrategy;

        public Builder() {
            this.type = ImageLoaderUtil.PIC_SMALL;
            this.url = "";
            this.placeHolder = R.mipmap.default_pic_big;
            this.imgView = null;
            this.loadStrategy = ImageLoaderUtil.LOAD_STRATEGY_NORMAL;
        }

        public Builder type(int type) {
            this.type = type;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder imgView(ImageView imgView) {
            this.imgView = imgView;
            return this;
        }

        public Builder strategy(int strategy) {
            this.loadStrategy = strategy;
            return this;
        }

        public ImageLoaderConfiguration build() {
            return new ImageLoaderConfiguration(this);
        }

    }


}
