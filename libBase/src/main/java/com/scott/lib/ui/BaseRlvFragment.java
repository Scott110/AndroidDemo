package com.scott.lib.ui;

import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.internal.ConstructorConstructor;
import com.scott.lib.callback.LoadingMoreCallback;
import com.scott.lib.config.RecyclerViewConfiguration;
import com.scott.lib.dBinding.adapter.BaseItemViewSelector;
import com.scott.lib.dBinding.adapter.BindingRecyclerViewAdapter;
import com.scott.lib.dBinding.adapter.ItemView;
import com.scott.lib.dBinding.adapter.ItemViewArg;
import com.scott.lib.dBinding.adapter.ItemViewSelector;
import com.scott.lib.widget.recyclerView.LoadingMoreFooter;
import com.scott.lib.widget.recyclerView.XRecyclerView;
import com.scott.libstyle.DbindingEventCallback;
import com.scott.libstyle.EventHandler;
import com.scott.util.StringUtils;

import java.lang.reflect.Constructor;

/**
 * author: heshantao
 * data: 2017/1/22.
 */

public abstract class BaseRlvFragment<T> extends BaseFragment implements LoadingMoreCallback {
    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }


    //配置RecyclerView
    public void configRecyclerView(RecyclerViewConfiguration confg) {
        if (confg == null) return;
        ItemViewArg arg = null;
        BindingRecyclerViewAdapter adapter = null;
        recyclerView = confg.getmRlv();
        RecyclerView.LayoutManager layoutManager = confg.getLayoutManager();
        RecyclerView.ItemAnimator animator = confg.getAnimator();
        adapter = confg.getAdapter();
        ItemView itemView = confg.getItemView();
        ObservableArrayList list = confg.getItems();
        ItemViewSelector selector = confg.getSelector();
        RecyclerView.ItemDecoration decoration = confg.getDecor();
        String adapterName = confg.getAdapterName();
        if (recyclerView == null || layoutManager == null) return;

        if (itemView != null) {
            arg = ItemViewArg.of(itemView);
        }

        if (selector != null) {
            arg = ItemViewArg.of(selector);
        }

        if (adapter == null) {
            if (!StringUtils.isEmpty(adapterName)) {
                adapter = create(adapterName, arg);
            }
        }
        if (adapter == null) return;
        adapter.onAttachedToRecyclerView(recyclerView);
        adapter.setItems(list);
        recyclerView.setLayoutManager(layoutManager);

        if (animator != null) {
            recyclerView.setItemAnimator(animator);
        }

        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(adapter);

    }


    //通过反射获得Adapter类名
    public BindingRecyclerViewAdapter create(String clazzName, ItemViewArg arg) {
        try {
            Class clazz = Class.forName(clazzName);
            Constructor<? extends BindingRecyclerViewAdapter> adapter =
                    clazz.getDeclaredConstructor(ItemViewArg.class);
            return adapter.newInstance(arg);
        } catch (Throwable e) {
            throw new RuntimeException(
                    "Unable to create Adapter for" + clazzName + e.getCause().getMessage(), e);
        }

    }

    public void setRecyclerView(RecyclerView mRecyclerView) {
        recyclerView = mRecyclerView;
    }

    //设置加载更多
    public void setLoadingMore() {
        if (recyclerView == null) return;
        if (recyclerView instanceof XRecyclerView) {
            ((XRecyclerView) recyclerView).setLoadingListener(this);
            View footView = ((XRecyclerView) recyclerView).getFootView();
            if (footView instanceof LoadingMoreFooter) {
                ((LoadingMoreFooter) footView)
                        .setEventHandler(new EventHandler(_mActivity, new DbindingEventCallback<T>() {
                            @Override
                            public void onViewClick(T t) {

                            }

                            @Override
                            public void onViewClick() {
                                retry();
                            }

                            @Override
                            public void onViewLongClick(T t) {

                            }

                            @Override
                            public void onViewLongClick() {

                            }
                        }));
            }
        }
    }

    @Override
    public void retry() {
        Toast.makeText(_mActivity, "重试", Toast.LENGTH_SHORT).show();
    }

    public abstract void requestData();

    @Override
    public void onLoadMore() {
        requestData();
    }
}
