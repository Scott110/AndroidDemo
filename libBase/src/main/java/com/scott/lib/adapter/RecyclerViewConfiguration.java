package com.scott.lib.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

/**
 * author: heshantao
 * data: 2017/1/13.
 * RecyclerView 配置信息
 */

public class RecyclerViewConfiguration {
    private List<Object> items;
    private BaseEasyViewHolderFactory viewHolderFactory;
    private HashMap<Class, Class<? extends EasyViewHolder>> boundViewHolders;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemDecoration decor;
    private RecyclerView.ItemAnimator animator;
    private int spanSizeLookup;

    public RecyclerViewConfiguration(Builder builder) {
        this.items = builder.items;
        this.viewHolderFactory = builder.viewHolderFactory;
        this.boundViewHolders = builder.boundViewHolders;
        this.layoutManager = builder.layoutManager;
        this.decor = builder.decor;
        this.animator = builder.animator;
        this.spanSizeLookup = builder.spanSizeLookup;
    }


    public RecyclerView.ItemAnimator getAnimator() {
        return animator;
    }

    public void setAnimator(RecyclerView.ItemAnimator animator) {
        this.animator = animator;
    }

    public HashMap<Class, Class<? extends EasyViewHolder>> getBoundViewHolders() {
        return boundViewHolders;
    }

    public void setBoundViewHolders(HashMap<Class, Class<? extends EasyViewHolder>> boundViewHolders) {
        this.boundViewHolders = boundViewHolders;
    }

    public RecyclerView.ItemDecoration getDecor() {
        return decor;
    }

    public void setDecor(RecyclerView.ItemDecoration decor) {
        this.decor = decor;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public int getSpanSizeLookup() {
        return spanSizeLookup;
    }

    public void setSpanSizeLookup(int spanSizeLookup) {
        this.spanSizeLookup = spanSizeLookup;
    }

    public BaseEasyViewHolderFactory getViewHolderFactory() {
        return viewHolderFactory;
    }

    public void setViewHolderFactory(BaseEasyViewHolderFactory viewHolderFactory) {
        this.viewHolderFactory = viewHolderFactory;
    }

    public static class Builder {
        private List<Object> items;
        private BaseEasyViewHolderFactory viewHolderFactory;
        private HashMap<Class, Class<? extends EasyViewHolder>> boundViewHolders;
        private RecyclerView.LayoutManager layoutManager;
        private RecyclerView.ItemDecoration decor;
        private RecyclerView.ItemAnimator animator;
        private int spanSizeLookup;

        public Builder() {

        }

        public Builder items(List<Object> items) {
            this.items = items;
            return this;
        }

        public Builder viewHolderFactory(BaseEasyViewHolderFactory viewHolderFactory) {
            this.viewHolderFactory = viewHolderFactory;
            return this;
        }

        public Builder bind(Class valueClass, Class<? extends EasyViewHolder> viewHolder) {
            boundViewHolders.put(valueClass, viewHolder);
            return this;
        }

        public Builder recyclerLayoutManager(RecyclerView.LayoutManager layoutManager) {
            this.layoutManager = layoutManager;
            return this;
        }

        public Builder recyclerViewDecor(RecyclerView.ItemDecoration decor) {
            this.decor = decor;
            return this;
        }

        public Builder recyclerViewAnimator(RecyclerView.ItemAnimator animator) {
            this.animator = animator;
            return this;
        }


        public Builder spanSizeLookup(int spanSizeLookup) {
            this.spanSizeLookup = spanSizeLookup;
            return this;
        }

        public RecyclerViewConfiguration build() {
            return new RecyclerViewConfiguration(this);
        }
    }
}
