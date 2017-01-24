package com.scott.demo;

import com.scott.demo.bean.Person;
import com.scott.libhttp.ben.HttpResponseEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * author: heshantao
 * data: 2017/1/23.
 */

public interface PersonService {
    @GET("/issue/{id}")
    Observable<HttpResponseEntity<List<Person>>> getPerson(@Path("id") String id);
}
