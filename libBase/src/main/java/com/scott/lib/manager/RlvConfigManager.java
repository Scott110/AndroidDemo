package com.scott.lib.manager;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
    private ItemDecoManager decoManager;
    private ItemAnimManager animManager;
    private LayoutManagers layoutManagers;

    public RlvConfigManager(ItemDecoManager decoManager, ItemAnimManager animManager, LayoutManagers layoutManagers) {
        this.animManager = animManager;
        this.decoManager = decoManager;
        this.layoutManagers = layoutManagers;
    }


    //获得默认线性RecyclerView配置信息
    public RecyclerViewConfiguration DEFAULT(RecyclerView recyclerView, ItemView itemView, ObservableArrayList<T> list) {
        Context context = recyclerView.getContext();
        RecyclerView.LayoutManager mLayoutManager = layoutManagers.linear().create(recyclerView);
        ItemViewArg<T> arg = ItemViewArg.of(itemView);
        BindingRecyclerViewAdapter<T> adapter = BindingRecyclerViewAdapterFactory.DEFAULT.create(recyclerView, arg);
        RecyclerView.ItemDecoration decoration = decoManager.DEFAULT(context);
        RecyclerView.ItemAnimator animator = animManager.DEFAULT();
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


    //获得默认复杂view的RecyclerView配置信息
    public RecyclerViewConfiguration mutileRecyclerConfig(RecyclerView recyclerView, ItemViewSelector selector,
                                                          ObservableArrayList<T> list, String adapterName) {
        Context context = recyclerView.getContext();
        RecyclerView.LayoutManager mLayoutManager = layoutManagers.linear().create(recyclerView);
        RecyclerView.ItemDecoration decoration = decoManager.DEFAULT(context);
        RecyclerView.ItemAnimator animator = animManager.DEFAULT();
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
