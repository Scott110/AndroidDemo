package com.scott.demo;

import android.os.Bundle;

import com.scott.lib.base.ui.BaseFragment;

/**
 * author: heshantao
 * data: 2017/1/9.
 */

public class SeconderFragment extends BaseFragment {

    public static SeconderFragment newInstance() {
        return new SeconderFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_seconder;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }
}
