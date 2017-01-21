package com.scott.lib.dBinding.adapter;

import android.support.v4.view.ViewPager;

public interface BindingViewPagerAdapterFactory {
    <T> BindingViewPagerAdapter<T> create(ViewPager viewPager, ItemViewArg<T> arg);

    BindingViewPagerAdapterFactory DEFAULT = new BindingViewPagerAdapterFactory() {
        @Override
        public <T> BindingViewPagerAdapter<T> create(ViewPager viewPager, ItemViewArg<T> arg) {
            return new BindingViewPagerAdapter<>(arg);
        }
    };
}
