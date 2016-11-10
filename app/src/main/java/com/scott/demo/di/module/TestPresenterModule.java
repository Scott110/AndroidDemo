package com.scott.demo.di.module;

import com.scott.demo.mvp.contract.TestContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by scott_he on 16/10/17.
 */
@Module
public class TestPresenterModule {
    private final TestContract.View mView;

    public TestPresenterModule(TestContract.View view) {
        mView = view;
    }

    @Provides
    TestContract.View providerTestPresenterView() {
        return mView;
    }

}
