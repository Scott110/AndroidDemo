
package com.scott.demo.di.component;

import android.app.Application;
import com.scott.demo.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by scott_he on 16/10/17.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Application application();
}

