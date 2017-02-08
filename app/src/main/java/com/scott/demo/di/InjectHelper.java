package com.scott.demo.di;

import android.app.Application;
import android.content.Context;

import com.scott.demo.MyApplication;
import com.scott.demo.di.component.ApplicationComponent;
import com.scott.demo.di.component.DaggerApplicationComponent;
import com.scott.demo.di.component.DaggerFragmentComponent;
import com.scott.demo.di.component.FragmentComponent;
import com.scott.demo.di.module.ApplicationModule;
import com.scott.demo.di.module.FragmentModule;
import com.trello.rxlifecycle.LifecycleProvider;

/**
 * author: heshantao
 * data: 2017/2/5.
 * di Component提供类
 */

public class InjectHelper {

    public static FragmentComponent getFragmentComponent(Context context, LifecycleProvider lifecycleProvider) {
        return DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(lifecycleProvider))
                .applicationComponent(MyApplication.getApplication(context)
                        .getApplicationComponent()).build();
    }


    public static ApplicationComponent getApplicationComponent(Application application) {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application)).build();

    }


}
