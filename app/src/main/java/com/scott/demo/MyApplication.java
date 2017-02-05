package com.scott.demo;

import android.app.Application;
import android.content.Context;

import com.scott.demo.di.component.ApplicationComponent;
import com.scott.demo.di.component.DaggerApplicationComponent;
import com.scott.demo.di.component.DaggerRepositoryComponent;
import com.scott.demo.di.module.ApplicationModule;
import com.scott.lib.db.DbHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by scott_he on 16/10/17.
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //DaggerRepositoryComponent.builder().repositoryModule(new RepositoryModule("HTTP")).appModule
        //repositoryComponent=DaggerRepositoryComponent.builder().repositoryModule(new RepositoryModule("http")).applicationModule(new ApplicationModule(this)).build();
        //DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(MyApplication.this)).build();

        getApplicationComponent().inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        if (appComponent == null) {
            appComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        }
        return appComponent;
    }


    public static MyApplication getApplication(Context context) {
        return (MyApplication) context.getApplicationContext();
    }


    private void initRealm() {
        Realm.init(this);
        RealmConfiguration configuration = DbHelper.getInstance().getRealmConfiguration(getApplicationContext());
        Realm.setDefaultConfiguration(configuration);
    }
}
