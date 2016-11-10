package com.scott.demo.api;

import retrofit2.Retrofit;

/**
 * Created by scott_he on 16/10/17.
 */

public class Api {
    ApiService apiService;
    public static Api instance;

    public Api(Retrofit retrofit) {
        apiService = retrofit.create(ApiService.class);
    }

    public static Api getInstance(Retrofit retrofit) {
        if (instance == null)
            instance = new Api(retrofit);
        return instance;
    }
}
