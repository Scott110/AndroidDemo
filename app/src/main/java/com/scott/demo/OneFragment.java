package com.scott.demo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.scott.lib.ui.BaseFragment;

/**
 * author: heshantao
 * data: 2017/1/22.
 */

public class OneFragment extends BaseFragment {

    public static OneFragment getInstance() {
        return new OneFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_vp;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        vp binding = DataBindingUtil.setContentView(_mActivity, getLayoutId());
        binding.setTitle("第一个界面");
    }
}
