package com.scott.demo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.scott.lib.ui.BaseFragment;

/**
 * author: heshantao
 * data: 2017/1/22.
 */

public class TwoFragment extends BaseFragment {
    public static TwoFragment getInstance() {
        return new TwoFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_vp;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        vp binding=DataBindingUtil.setContentView(_mActivity, getLayoutId());
        binding.setTitle("第二个界面");
    }
}
