
package com.scott.demo.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by scott_he on 16/10/17.
 */

@Module
public final class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application app) {
        this.application = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }
}

