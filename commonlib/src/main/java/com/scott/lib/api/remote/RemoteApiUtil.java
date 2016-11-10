package com.scott.lib.api.remote;

import android.content.Context;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.scott.lib.api.remote.interceptor.CacheInterceptor;
import com.scott.lib.api.remote.interceptor.HeaderInterceptor;
import com.scott.lib.constant.LibConfig;
import com.scott.lib.util.CacheUtils;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by scott_he on 16/10/17.
 */

public class RemoteApiUtil {

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
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }


}
