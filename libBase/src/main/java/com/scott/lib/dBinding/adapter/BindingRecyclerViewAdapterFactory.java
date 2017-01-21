package com.scott.lib.dBinding.adapter;

import android.support.v7.widget.RecyclerView;

public interface BindingRecyclerViewAdapterFactory {
    <T> BindingRecyclerViewAdapter<T> create(RecyclerView recyclerView, ItemViewArg<T> arg);

    BindingRecyclerViewAdapterFactory DEFAULT = new BindingRecyclerViewAdapterFactory() {
        @Override
        public <T> BindingRecyclerViewAdapter<T> create(RecyclerView recyclerView, ItemViewArg<T> arg) {
            return new BindingRecyclerViewAdapter<>(arg);
        }
    };
}
