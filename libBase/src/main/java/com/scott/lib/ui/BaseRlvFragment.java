package com.scott.lib.ui;

import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.gson.internal.ConstructorConstructor;
import com.scott.lib.config.RecyclerViewConfiguration;
import com.scott.lib.dBinding.adapter.BaseItemViewSelector;
import com.scott.lib.dBinding.adapter.BindingRecyclerViewAdapter;
import com.scott.lib.dBinding.adapter.ItemView;
import com.scott.lib.dBinding.adapter.ItemViewArg;
import com.scott.lib.dBinding.adapter.ItemViewSelector;
import com.scott.util.StringUtils;

import java.lang.reflect.Constructor;

/**
 * author: heshantao
 * data: 2017/1/22.
 */

public class BaseRlvFragment extends BaseFragment {

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
        RecyclerView recyclerView = confg.getmRlv();
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
}
