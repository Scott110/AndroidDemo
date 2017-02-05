package com.scott.demo.simple.ui;

import android.os.Bundle;

import com.github.mzule.activityrouter.annotation.Router;
import com.scott.demo.R;
import com.scott.lib.ui.BaseActivity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * author: heshantao
 * data: 2017/2/5.
 */
@Router("di")
public class DiActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.acitivity_test;
    }

    @Override
    public SupportFragment getRootFragment() {
        return DiFragment.newInstance();
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }
}
