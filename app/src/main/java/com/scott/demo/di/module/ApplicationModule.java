
package com.scott.demo.di.module;

import android.app.Application;
import android.content.Context;

import com.scott.demo.di.qualifier.ApplicationContext;
import com.scott.lib.manager.ItemAnimManager;
import com.scott.lib.manager.ItemDecoManager;
import com.scott.lib.manager.LayoutManagers;
import com.scott.lib.manager.RlvConfigManager;
import com.scott.libhttp.manager.HttpManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpManager provideHttpManager() {
        return new HttpManager();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(HttpManager httpManager, @ApplicationContext Context context) {
        return httpManager.getRetrofit(httpManager.getOkhttpClient(context));
    }

    @Provides
    @Singleton
    ItemAnimManager provideItemAnimManager() {
        return new ItemAnimManager();
    }

    @Provides
    @Singleton
    ItemDecoManager provideItemDecoManager() {
        return new ItemDecoManager();
    }

    @Provides
    @Singleton
    LayoutManagers provideLayoutManagers() {
        return new LayoutManagers();
    }

    @Provides
    @Singleton
    RlvConfigManager provideRlvConfigManagers(ItemAnimManager animManager, ItemDecoManager decoManager, LayoutManagers layoutManagers) {
        return new RlvConfigManager(decoManager, animManager, layoutManagers);
    }


}

