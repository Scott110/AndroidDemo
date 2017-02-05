package com.scott.demo.di.component;

import com.scott.demo.di.module.FragmentModule;
import com.scott.demo.di.scope.FragmentScope;
import com.scott.demo.simple.ui.DiFragment;
import com.scott.lib.base.mvvm.IBaseView;
import com.trello.rxlifecycle.LifecycleProvider;

import dagger.Component;

/**
 * author: heshantao
 * data: 2017/2/5.
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(DiFragment fragment);
}
