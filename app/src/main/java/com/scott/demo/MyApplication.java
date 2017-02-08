package com.scott.demo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.scott.demo.di.InjectHelper;
import com.scott.demo.di.component.ApplicationComponent;
import com.scott.demo.di.component.DaggerApplicationComponent;
import com.scott.demo.di.component.DaggerRepositoryComponent;
import com.scott.demo.di.module.ApplicationModule;
import com.scott.lib.db.DbHelper;

import javax.inject.Inject;

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
        getApplicationComponent().inject(this);
        initRealm();
    }

    public ApplicationComponent getApplicationComponent() {
        return InjectHelper.getApplicationComponent(this);
    }


    public static MyApplication getApplication(Context context) {
        return (MyApplication) context.getApplicationContext();
    }


    /*
    *初始化数据库
    */
    private void initRealm() {
        Realm.init(this);
        RealmConfiguration configuration = new DbHelper().getRealmConfiguration(getApplicationContext());
        Realm.setDefaultConfiguration(configuration);
    }
}
