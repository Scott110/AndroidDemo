package com.scott.demo.di.module;

import android.content.Context;

import com.scott.lib.api.remote.RemoteApiUtil;
import com.scott.lib.util.CacheUtils;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by scott_he on 16/10/17.
 */
@Singleton
@Module
public class RepositoryModule {
    String baseUrl;
    Context mContext;

    public RepositoryModule(String url, Context context) {
        this.baseUrl = url;
        this.mContext = context;
    }


    @Provides
    public String provideString() {
        return baseUrl;
    }

    @Provides
    @Singleton
    protected OkHttpClient provideClient() {
        OkHttpClient client = RemoteApiUtil.getClient(mContext);
        return client;
    }

    @Provides
    @Singleton
    protected Retrofit provideRetrofit(OkHttpClient client) {
        Retrofit retrofit = RemoteApiUtil.getInstance(client, baseUrl);
        return retrofit;
    }

    @Provides
    protected File provideCacheFile() {
        File file = CacheUtils.getHttpCacheFile(mContext);
        return file;
    }

   /* @Provides
    @Singleton
    protected ApiCacheProvider provideProvider(File file) {
        ApiCacheProvider cacheProvider = LocalApiUtil.getInstance(ApiCacheProvider.class,file);
        return cacheProvider;
    }

    @Provides
    protected Repository provideRepository(Provider provider, Retrofit retrofit) {
        Api api = Api.getInstance(retrofit);
        return new Repository(api, provider);
    }*/

}
