package com.scott.libhttp.manager;

import android.content.Context;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.scott.libhttp.LibConfig;
import com.scott.libhttp.api.BaseApi;
import com.scott.libhttp.interceptor.CacheInterceptor;
import com.scott.libhttp.interceptor.HeaderInterceptor;
import com.scott.util.CacheUtils;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: heshantao
 * data: 2017/1/18.
 */

public class HttpManager {

    public static OkHttpClient getClient(Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (LibConfig.DEBUG) {
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


    public void doHttpDeal(BaseApi basePar) {

       /* *//*rx处理*//*
        RxSubscriber subscriber = new RxSubscriber(basePar);
        Observable observable = basePar.getObservable(retrofit)
                *//*失败后的retry配置*//*
                .retryWhen(new RetryWhenNetworkException())
                *//*生命周期管理*//*
//                .compose(basePar.getRxAppCompatActivity().bindToLifecycle())
                .compose(basePar.getRxAppCompatActivity().bindUntilEvent(ActivityEvent.DESTROY))
                *//*http请求线程*//*
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                *//*回调线程*//*
                .observeOn(AndroidSchedulers.mainThread())
                *//*结果判断*//*
                .map(basePar);

        *//*数据回调*//*
        observable.subscribe(subscriber);*/
    }
}
