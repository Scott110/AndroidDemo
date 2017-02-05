package com.scott.demo.di.module;

import com.scott.demo.di.scope.FragmentScope;
import com.scott.lib.base.mvvm.IBaseView;
import com.trello.rxlifecycle.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * author: heshantao
 * data: 2017/2/5.
 */

@Module
public class FragmentModule {
    LifecycleProvider lifecycleProvider;

    public FragmentModule(LifecycleProvider lifecycleProvider) {
        this.lifecycleProvider = lifecycleProvider;
    }

    @Provides
    @FragmentScope
    public LifecycleProvider getLifecycleProvider() {
        return lifecycleProvider;
    }


    @Provides
    public IBaseView getView() {
        return (IBaseView) lifecycleProvider;
    }
}
