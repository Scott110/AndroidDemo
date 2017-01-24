package com.scott.demo;

import android.os.Bundle;

import com.github.mzule.activityrouter.annotation.Router;
import com.scott.lib.ui.BaseActivity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * author: heshantao
 * data: 2017/1/22.
 */
@Router("third")
public class ThirdActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.acitivity_test;
    }

    @Override
    public SupportFragment getRootFragment() {
        return ThirdFragment.newInstance();
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    private void getPersonInfo(){

    }
}
