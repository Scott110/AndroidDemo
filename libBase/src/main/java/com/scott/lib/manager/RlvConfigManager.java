package com.scott.lib.manager;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;

import com.scott.lib.config.RecyclerViewConfiguration;
import com.scott.lib.dBinding.adapter.BindingRecyclerViewAdapter;
import com.scott.lib.dBinding.adapter.OnItemBind;

/**
 * author: heshantao
 * data: 2017/1/21.
 * RecyclerView 属性管理器
 */

public class RlvConfigManager<T> {
    private static final String TAG = RlvConfigManager.class.getSimpleName();
    private ItemDecoManager decoManager;
    private ItemAnimManager animManager;
    private LayoutManagers layoutManagers;

    public RlvConfigManager(ItemDecoManager decoManager, ItemAnimManager animManager, LayoutManagers layoutManagers) {
        this.animManager = animManager;
        this.decoManager = decoManager;
        this.layoutManagers = layoutManagers;
    }


    //获得默认线性RecyclerView配置信息
    public RecyclerViewConfiguration DEFAULT(RecyclerView recyclerView, OnItemBind itemBind, ObservableArrayList<T> list) {
        Context context = recyclerView.getContext();
        RecyclerView.LayoutManager mLayoutManager = layoutManagers.linear().create(recyclerView);
        BindingRecyclerViewAdapter<T> adapter = new BindingRecyclerViewAdapter<>();
        RecyclerView.ItemDecoration decoration = decoManager.DEFAULT(context);
        RecyclerView.ItemAnimator animator = animManager.DEFAULT();
        RecyclerViewConfiguration config = new RecyclerViewConfiguration
                .Builder().setAdapter(adapter)
                .setItems(list)
                .setItemBind(itemBind)
                .setLayoutManager(mLayoutManager)
                .setRecyclerView(recyclerView)
                .setDecor(decoration)
                .setAnimator(animator)
                .build();
        return config;
    }


    //获得默认复杂view的RecyclerView配置信息
    public RecyclerViewConfiguration mutileRecyclerConfig(RecyclerView recyclerView, OnItemBind itemBind,
                                                          ObservableArrayList<T> list) {
        Context context = recyclerView.getContext();
        RecyclerView.LayoutManager mLayoutManager = layoutManagers.linear().create(recyclerView);
        RecyclerView.ItemDecoration decoration = decoManager.DEFAULT(context);
        RecyclerView.ItemAnimator animator = animManager.DEFAULT();
        BindingRecyclerViewAdapter<T> adapter = new BindingRecyclerViewAdapter<>();
        RecyclerViewConfiguration config = new RecyclerViewConfiguration
                .Builder()
                .setItems(list)
                .setItemBind(itemBind)
                .setLayoutManager(mLayoutManager)
                .setRecyclerView(recyclerView)
                .setDecor(decoration)
                .setAnimator(animator)
                .setAdapter(adapter)
                .build();
        return config;
    }
}
