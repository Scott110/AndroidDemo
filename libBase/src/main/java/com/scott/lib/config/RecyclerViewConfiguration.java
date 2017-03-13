package com.scott.lib.config;

import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;

import com.scott.lib.dBinding.adapter.BindingRecyclerViewAdapter;
import com.scott.lib.dBinding.adapter.OnItemBind;


/**
 * author: heshantao
 * data: 2017/1/13.
 * RecyclerView 配置信息
 */

public class RecyclerViewConfiguration<T> {
    //item 数据信息
    private ObservableArrayList<T> items;
    //装饰
    private RecyclerView.ItemDecoration decor;
    //动画
    private RecyclerView.ItemAnimator animator;
    //RecyclerView
    private RecyclerView mRlv;
    //layoutManager
    private RecyclerView.LayoutManager layoutManager;
    //recyclerVie item binding
    private OnItemBind<T> itemBind;
    /*
    * 另一种是继承 BindingRecyclerViewAdapter  然后在里面可以处理各种事件(如绑定点击事件)
    * */
    private BindingRecyclerViewAdapter adapter;


    public RecyclerViewConfiguration(Builder builder) {
        this.items = builder.items;
        this.decor = builder.decor;
        this.animator = builder.animator;
        this.mRlv = builder.mRlv;
        this.adapter = builder.adapter;
        this.layoutManager = builder.layoutManager;
        this.itemBind = builder.itemBind;
    }


    public RecyclerView.ItemAnimator getAnimator() {
        return animator;
    }

    public void setAnimator(RecyclerView.ItemAnimator animator) {
        this.animator = animator;
    }


    public RecyclerView.ItemDecoration getDecor() {
        return decor;
    }

    public void setDecor(RecyclerView.ItemDecoration decor) {
        this.decor = decor;
    }

    public BindingRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BindingRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    public ObservableArrayList<T> getItems() {
        return items;
    }

    public void setItems(ObservableArrayList<T> items) {
        this.items = items;
    }

    public OnItemBind<T> getItemBind() {
        return itemBind;
    }

    public void setItemBind(OnItemBind<T> itemBind) {
        this.itemBind = itemBind;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public RecyclerView getmRlv() {
        return mRlv;
    }

    public void setmRlv(RecyclerView mRlv) {
        this.mRlv = mRlv;
    }


    public static class Builder<T> {
        private ObservableArrayList<T> items;
        private RecyclerView mRlv;
        private RecyclerView.LayoutManager layoutManager;
        private RecyclerView.ItemDecoration decor;
        private RecyclerView.ItemAnimator animator;
        private OnItemBind<T> itemBind;
        private BindingRecyclerViewAdapter<T> adapter;

        public Builder() {

        }

        public Builder setItems(ObservableArrayList<T> items) {
            this.items = items;
            return this;
        }

        public Builder setRecyclerView(RecyclerView recyclerView) {
            this.mRlv = recyclerView;
            return this;
        }

        public Builder setLayoutManager(RecyclerView.LayoutManager manager) {
            this.layoutManager = manager;
            return this;
        }


        public Builder setDecor(RecyclerView.ItemDecoration decor) {
            this.decor = decor;
            return this;
        }

        public Builder setAnimator(RecyclerView.ItemAnimator animator) {
            this.animator = animator;
            return this;
        }


        public Builder setItemBind(OnItemBind<T> itemBind) {
            this.itemBind = itemBind;
            return this;
        }

        public Builder setAdapter(BindingRecyclerViewAdapter mAdapter) {
            this.adapter = mAdapter;
            return this;
        }


        public RecyclerViewConfiguration build() {
            return new RecyclerViewConfiguration(this);
        }
    }
}
