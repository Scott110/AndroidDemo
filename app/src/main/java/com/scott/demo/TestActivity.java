package com.scott.demo;

import com.scott.lib.ui.BaseActivity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by scott_he on 16/10/17.
 */

public class TestActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.acitivity_test;
    }

    @Override
    public SupportFragment getRootFragment() {
        return TestFragment.newInstance();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

}
