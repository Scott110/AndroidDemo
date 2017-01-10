package com.scott.demo;

import android.content.Intent;
import android.os.Bundle;

import com.github.mzule.activityrouter.router.Routers;
import com.scott.lib.base.ui.BaseFragment;

import butterknife.OnClick;

/**
 * author: heshantao
 * data: 2017/1/9.
 */

public class TestFragment extends BaseFragment {

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @OnClick(R.id.skip_btn)
    void onClick() {
        Routers.open(getActivity(), "scott://seconder/12");
        //Intent intent = new Intent(getActivity(), SeconderActivity.class);
        //startActivity(intent);
    }
}
