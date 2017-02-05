package com.scott.demo;

import android.os.Bundle;

import com.github.mzule.activityrouter.router.Routers;
import com.scott.lib.ui.BaseFragment;

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


    @OnClick(R.id.skip_third_btn)
    void onThirdClick() {
        Routers.open(getActivity(), "scott://third");
    }


    @OnClick(R.id.di_btn)
    void onDClick() {
        Routers.open(getActivity(), "scott://di");
    }
}
