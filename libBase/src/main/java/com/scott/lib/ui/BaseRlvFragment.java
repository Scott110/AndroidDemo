package com.scott.lib.ui;

import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.scott.lib.callback.LoadingMoreCallback;
import com.scott.lib.config.RecyclerViewConfiguration;
import com.scott.lib.dBinding.adapter.BindingRecyclerViewAdapter;
import com.scott.lib.dBinding.adapter.ItemBinding;
import com.scott.lib.dBinding.adapter.OnItemBind;
import com.scott.lib.widget.recyclerView.LoadingMoreFooter;
import com.scott.lib.widget.recyclerView.XRecyclerView;
import com.scott.libstyle.DbindingEventCallback;
import com.scott.libstyle.EventHandler;

/**
 * author: heshantao
 * data: 2017/1/22.
 */

public abstract class BaseRlvFragment<T> extends BaseFragment implements LoadingMoreCallback {
    public BindingRecyclerViewAdapter mAdapter;
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
        ItemBinding binding = null;
        recyclerView = confg.getmRlv();
        RecyclerView.LayoutManager layoutManager = confg.getLayoutManager();
        RecyclerView.ItemAnimator animator = confg.getAnimator();
        mAdapter = confg.getAdapter();
        OnItemBind onItemBind = confg.getItemBind();
        ObservableArrayList list = confg.getItems();
        RecyclerView.ItemDecoration decoration = confg.getDecor();
        if (recyclerView == null || layoutManager == null) return;

        if (onItemBind != null) {
            binding = ItemBinding.of(onItemBind);
        }

        if (mAdapter == null) return;
        mAdapter.setItemBinding(binding);
        //mAdapter.onAttachedToRecyclerView(recyclerView);
        mAdapter.setItems(list);
        recyclerView.setLayoutManager(layoutManager);

        if (animator != null) {
            recyclerView.setItemAnimator(animator);
        }

        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(mAdapter);

    }



    /*//通过反射获得Adapter类名
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

    }*/

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
                            public void onViewClick(View view, T t) {

                            }

                            @Override
                            public void onViewClick(View view) {
                                retry();
                            }

                            @Override
                            public void onViewLongClick(View view, T t) {

                            }

                            @Override
                            public void onViewLongClick(View view) {

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
