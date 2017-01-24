package com.scott.demo;

import android.os.Bundle;
import android.widget.Toast;

import com.github.mzule.activityrouter.annotation.Router;
import com.scott.lib.ui.BaseActivity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * author: heshantao
 * data: 2017/1/9.
 */

@Router("seconder/:age")
public class SeconderActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.acitivity_test;
    }

    @Override
    public SupportFragment getRootFragment() {
        return SeconderFragment.newInstance();
    }

    @Override
    public void init(Bundle savedInstanceState) {
        String age = getIntent().getStringExtra("age");
        Toast.makeText(this, "多大了" + age, Toast.LENGTH_SHORT).show();
    }

}
