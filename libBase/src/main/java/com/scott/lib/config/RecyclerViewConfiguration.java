package com.scott.lib.config;

import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;

import com.scott.lib.dBinding.adapter.BindingRecyclerViewAdapter;
import com.scott.lib.dBinding.adapter.ItemView;
import com.scott.lib.dBinding.adapter.ItemViewSelector;

import java.util.HashMap;
import java.util.List;

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
    /*
    *
    *带绑定的item 适用于一种类型的View
    * subStu是xml中绑定的实体类 item_text 是item的xml
    * ItemView itemView = ItemView.of(BR.subStu, R.layout.item_text);
    * */
    private ItemView itemView;
    /*
    * 适用于RecyclerView中多个样式的View情况
    *
    * BaseItemViewSelector<Person> selector = new BaseItemViewSelector<Person>() {
            @Override
            public void select(ItemView itemView, int position, Person item) {
                if (position % 2 == 0) {
                    itemView.set(BR.person, R.layout.item_text);
                } else {
                    itemView.set(BR.person, R.layout.item_red_txt);
                }
            }
        };
    * 更具实际情况设置不同的View
    *
    *
    * */
    private ItemViewSelector<T> selector;
    /*
    * 适配器 可以通过BindingRecyclerViewAdapterFactory.DEFAULT.create() 创建
    * 另一种是继承 BindingRecyclerViewAdapter  然后在里面可以处理各种事件(如绑定点击事件)
    * */
    private BindingRecyclerViewAdapter adapter;
    //自定义Adapter名称包含包名"com.scott.demo.adapter.CustomerAdapter"
    private String adapterName;


    public RecyclerViewConfiguration(Builder builder) {
        this.items = builder.items;
        this.decor = builder.decor;
        this.animator = builder.animator;
        this.mRlv = builder.mRlv;
        this.adapter = builder.adapter;
        this.layoutManager = builder.layoutManager;
        this.itemView = builder.itemView;
        this.selector = builder.selector;
        this.adapterName = builder.adapterName;
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

    public ItemView getItemView() {
        return itemView;
    }

    public void setItemView(ItemView itemView) {
        this.itemView = itemView;
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

    public ItemViewSelector<T> getSelector() {
        return selector;
    }

    public void setSelector(ItemViewSelector<T> selector) {
        this.selector = selector;
    }

    public String getAdapterName() {
        return adapterName;
    }

    public void setAdapterName(String adapterName) {
        this.adapterName = adapterName;
    }

    public static class Builder<T> {
        private ObservableArrayList<T> items;
        private RecyclerView mRlv;
        private RecyclerView.LayoutManager layoutManager;
        private RecyclerView.ItemDecoration decor;
        private RecyclerView.ItemAnimator animator;
        private ItemView itemView;
        private ItemViewSelector<T> selector;
        private BindingRecyclerViewAdapter<T> adapter;
        private String adapterName;

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


        public Builder setItemView(ItemView view) {
            this.itemView = view;
            return this;
        }

        public Builder setItemViewSelector(ItemViewSelector mSelector) {
            this.selector = mSelector;
            return this;
        }

        public Builder setAdapter(BindingRecyclerViewAdapter mAdapter) {
            this.adapter = mAdapter;
            return this;
        }

        public Builder setAdapterName(String adapterName) {
            this.adapterName = adapterName;
            return this;
        }

        public RecyclerViewConfiguration build() {
            return new RecyclerViewConfiguration(this);
        }
    }
}
