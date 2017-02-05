package com.scott.demo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.scott.lib.ui.BaseFragment;
import com.scott.libstyle.loadMore;

/**
 * author: heshantao
 * data: 2017/1/22.
 */

public class FourFragment extends BaseFragment {

    public static FourFragment getInstance() {
        return new FourFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_vp;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        vp binding = DataBindingUtil.setContentView(_mActivity, getLayoutId());
        binding.setTitle("第四个界面");
        
    }
}
