package com.scott.lib.manager;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;

import com.scott.lib.config.RecyclerViewConfiguration;
import com.scott.lib.dBinding.adapter.BindingRecyclerViewAdapter;
import com.scott.lib.dBinding.adapter.BindingRecyclerViewAdapterFactory;
import com.scott.lib.dBinding.adapter.ItemView;
import com.scott.lib.dBinding.adapter.ItemViewArg;
import com.scott.lib.dBinding.adapter.ItemViewSelector;

/**
 * author: heshantao
 * data: 2017/1/21.
 * RecyclerView 属性管理器
 */

public class RlvConfigManager<T> {
    private static final String TAG = RlvConfigManager.class.getSimpleName();
    private volatile static RlvConfigManager INSTANCE;

    //构造方法私有
    private RlvConfigManager() {
    }

    //获取单例
    public static RlvConfigManager getInstance() {
        if (INSTANCE == null) {
            synchronized (RlvConfigManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RlvConfigManager();
                }
            }
        }
        return INSTANCE;
    }


    //获得默认线性RecyclerView配置信息
    public RecyclerViewConfiguration DEFAULT(RecyclerView recyclerView, ItemView itemView, ObservableArrayList<T> list) {
        Context context = recyclerView.getContext();
        RecyclerView.LayoutManager mLayoutManager = LayoutManagers.linear().create(recyclerView);
        ItemViewArg<T> arg = ItemViewArg.of(itemView);
        BindingRecyclerViewAdapter<T> adapter = BindingRecyclerViewAdapterFactory.DEFAULT.create(recyclerView, arg);
        RecyclerView.ItemDecoration decoration = ItemDecoManager.getInstance().DEFAULT(context);
        RecyclerView.ItemAnimator animator = ItemAnimManager.getInstance().DEFAULT();
        RecyclerViewConfiguration config = new RecyclerViewConfiguration
                .Builder().setAdapter(adapter)
                .setItems(list)
                .setItemView(itemView)
                .setLayoutManager(mLayoutManager)
                .setRecyclerView(recyclerView)
                .setDecor(decoration)
                .setAnimator(animator)
                .build();
        return config;
    }


    //获得默认线性RecyclerView配置信息
    public RecyclerViewConfiguration mutileRecyclerConfig(RecyclerView recyclerView, ItemViewSelector selector,
                                                          ObservableArrayList<T> list, String adapterName) {
        Context context = recyclerView.getContext();
        RecyclerView.LayoutManager mLayoutManager = LayoutManagers.linear().create(recyclerView);
        RecyclerView.ItemDecoration decoration = ItemDecoManager.getInstance().DEFAULT(context);
        RecyclerView.ItemAnimator animator = ItemAnimManager.getInstance().DEFAULT();
        RecyclerViewConfiguration config = new RecyclerViewConfiguration
                .Builder()
                .setItems(list)
                .setItemViewSelector(selector)
                .setLayoutManager(mLayoutManager)
                .setRecyclerView(recyclerView)
                .setDecor(decoration)
                .setAnimator(animator)
                .setAdapterName(adapterName)
                .build();
        return config;
    }
}
