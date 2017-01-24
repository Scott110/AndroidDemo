package com.scott.libhttp.manager;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.scott.libhttp.constant.Constants;
import com.scott.libhttp.api.BaseApi;
import com.scott.libhttp.interceptor.CacheInterceptor;
import com.scott.libhttp.interceptor.HeaderInterceptor;
import com.scott.libhttp.rx.RetryWhenFun;
import com.scott.libhttp.subscriber.RxSubscriber;
import com.scott.util.CacheUtils;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;

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

public class HttpManager {

    public static OkHttpClient getClient(Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (Constants.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        long maxCacheSize = 1000 * 1000 * 50;
        Cache cache = new Cache(CacheUtils.getOkHttpFile(context), maxCacheSize);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cookieJar(new PersistentCookieJar(new SetCookieCache(),
                        new SharedPrefsCookiePersistor(context)))//session,cookie 持久化
                .addInterceptor(new HeaderInterceptor(context))
                .cache(cache) //缓存 okhttp3 本来是存在的不设置不起作用
                .addInterceptor(new CacheInterceptor(context))
                .addInterceptor(new HttpLoggingInterceptor());
        return builder.build();
    }

    public static Retrofit getInstance(OkHttpClient client, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }


    public static void doHttpDeal(BaseApi basePar, Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(basePar.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getClient(context))
                .build();

        RxSubscriber subscriber = new RxSubscriber(basePar);
        Observable observable = basePar.getObservable(retrofit)
                .retryWhen(new RetryWhenFun())
                .compose(basePar.getLifeProvider() instanceof Fragment ?
                        basePar.getLifeProvider().bindUntilEvent(FragmentEvent.DESTROY):
                        basePar.getLifeProvider().bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(basePar);
        observable.subscribe(subscriber);
    }
}
