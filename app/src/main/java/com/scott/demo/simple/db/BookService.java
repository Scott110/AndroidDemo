package com.scott.demo.simple.db;

import com.scott.demo.bean.Person;
import com.scott.libhttp.ben.HttpResponseEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * author: heshantao
 * data: 2017/2/7.
 */

public interface BookService {
    @GET("/issue/{id}")
    Observable<HttpResponseEntity<List<BookBean>>> getBook(@Path("id") String id);
}
