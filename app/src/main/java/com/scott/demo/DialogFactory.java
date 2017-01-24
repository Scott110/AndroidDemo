package com.scott.demo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.scott.lib.widget.dialog.PermissionSettingDialog;
import com.scott.libhttp.api.BaseApi;
import com.scott.libhttp.constant.Constants;
import com.scott.libhttp.interceptor.CacheInterceptor;
import com.scott.libhttp.interceptor.HeaderInterceptor;
import com.scott.libhttp.rx.RetryWhenFun;
import com.scott.libhttp.subscriber.RxSubscriber;
import com.scott.util.CacheUtils;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.FragmentEvent;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author: heshantao
 * data: 2017/1/18.
 */

public class DialogFactory {

    private static final String DIALOG_PREMISSION_TAG = "permission_dialog";
    FragmentManager mFragmentManager;

    public DialogFactory(FragmentManager manager) {
        this.mFragmentManager = manager;
    }


    public void showPremissionSettingDialog(String title, String msg, String okStr, String navStr) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Fragment fragment = mFragmentManager.findFragmentByTag(DIALOG_PREMISSION_TAG);
        if (null != fragment) {
            ft.remove(fragment).commit();
        }
        PermissionSettingDialog dialog = PermissionSettingDialog.newInstance(title, msg, okStr, navStr);
        dialog.show(mFragmentManager, DIALOG_PREMISSION_TAG);
        mFragmentManager.executePendingTransactions();
    }

}
