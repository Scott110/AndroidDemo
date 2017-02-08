package com.scott.demo.simple.db;

import android.os.Bundle;

import com.github.mzule.activityrouter.annotation.Router;
import com.scott.demo.R;
import com.scott.lib.ui.BaseActivity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * author: heshantao
 * data: 2017/2/6.
 */

@Router("db")
public class DbActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.acitivity_test;
    }

    @Override
    public SupportFragment getRootFragment() {
        return DbFragment.newInstance();
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }
}
