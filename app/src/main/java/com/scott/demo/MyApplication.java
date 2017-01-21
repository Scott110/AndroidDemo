package com.scott.demo;

import android.app.Application;

import com.scott.lib.db.DbHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by scott_he on 16/10/17.
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        //DaggerRepositoryComponent.builder().repositoryModule(new RepositoryModule("HTTP")).appModule
        //repositoryComponent=DaggerRepositoryComponent.builder().repositoryModule(new RepositoryModule("http")).applicationModule(new ApplicationModule(this)).build();
        //DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(MyApplication.this)).build();


    }


    private void initRealm() {
        Realm.init(this);
        RealmConfiguration configuration = DbHelper.getInstance().getRealmConfiguration(getApplicationContext());
        Realm.setDefaultConfiguration(configuration);
    }
}
