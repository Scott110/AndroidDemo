
package com.scott.demo.di.component;

import android.app.Application;
import android.content.Context;

import com.scott.demo.di.module.ApplicationModule;
import com.scott.demo.di.qualifier.ApplicationContext;
import com.scott.lib.manager.RlvConfigManager;
import com.scott.libhttp.manager.HttpManager;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


/**
 * Created by scott_he on 16/10/17.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(Application application);

    @ApplicationContext
    Context context();


    HttpManager httpManager();

    Retrofit retrofit();

    RlvConfigManager rlvConfigManager();

}

