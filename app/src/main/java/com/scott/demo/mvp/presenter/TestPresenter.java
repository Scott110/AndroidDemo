package com.scott.demo.mvp.presenter;

import android.util.Log;

import com.scott.demo.api.Repository;
import com.scott.demo.mvp.contract.TestContract;

import javax.inject.Inject;

/**
 * Created by scott_he on 16/10/17.
 */

public class TestPresenter implements TestContract.Presenter {
    private static final String TAG = "TestPresenter";

    Repository repository;

    @Inject
    public TestPresenter(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void showData() {
        Log.d(TAG, "showData: ");
    }
}
