package com.scott.demo.di;

import android.content.Context;

import com.scott.demo.MyApplication;
import com.scott.demo.di.component.DaggerFragmentComponent;
import com.scott.demo.di.component.FragmentComponent;
import com.scott.demo.di.module.FragmentModule;
import com.trello.rxlifecycle.LifecycleProvider;

/**
 * author: heshantao
 * data: 2017/2/5.
 */

public class InjectHelper {

    public static FragmentComponent getFragmentComponent(Context context, LifecycleProvider lifecycleProvider) {
        return DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(lifecycleProvider))
                .applicationComponent(MyApplication.getApplication(context)
                        .getApplicationComponent()).build();
    }
}
