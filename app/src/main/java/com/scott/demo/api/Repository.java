package com.scott.demo.api;

/**
 * Created by scott_he on 16/10/17.
 */

public class Repository {
    Api api;
    ApiCacheProvider mProvider;

    public Repository(Api api, ApiCacheProvider mProvider) {
        this.api = api;
        this.mProvider = mProvider;
    }


}
