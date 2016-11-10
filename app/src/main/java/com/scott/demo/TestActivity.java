package com.scott.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.scott.demo.mvp.contract.TestContract;
import com.scott.lib.base.ui.BaseActivity;

import javax.inject.Inject;

/**
 * Created by scott_he on 16/10/17.
 */

public class TestActivity extends BaseActivity implements TestContract.View {
    private static final String TAG = "TestActivity";
    @Inject
    String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_test);
        //presenter.showData();
        //DaggerRepositoryComponent.builder().repositoryModule(new RepositoryModule("HTTP")).build().inject(this);
        Log.d(TAG, "onCreate: lllllllll" + url);
    }

    @Override
    public void setPresenter(TestContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showNetWorkErro() {

    }

    @Override
    public void showErro() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void retry() {

    }

    @Override
    public void restorView() {

    }
}
