package com.scott.demo.di.component;

import com.scott.demo.TestActivity;
import com.scott.demo.di.module.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by scott_he on 16/10/17.
 */
@Singleton
@Component(modules = RepositoryModule.class)
public interface RepositoryComponent {
    void inject(TestActivity activity);
}
