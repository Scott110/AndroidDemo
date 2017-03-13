package com.scott.demo.adapter;

import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

import com.scott.lib.dBinding.adapter.BindingRecyclerViewAdapter;

/**
 * author: heshantao
 * data: 2017/1/22.
 */

public class CustomerAdapter extends BindingRecyclerViewAdapter {
    String name = "11111";

    @Override
    public void onBindBinding(ViewDataBinding binding, int bindingVariable, @LayoutRes int layoutRes, int position, Object item) {
        super.onBindBinding(binding, bindingVariable, layoutRes, position, item);
    }

    public String getName() {
        return name;
    }
}
