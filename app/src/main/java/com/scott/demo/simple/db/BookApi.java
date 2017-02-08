package com.scott.demo.simple.db;

import com.scott.libhttp.api.BaseApi;
import com.trello.rxlifecycle.LifecycleProvider;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * author: heshantao
 * data: 2017/2/7.
 */

public class BookApi extends BaseApi<BookBean> {

    String id;

    @Inject
    public BookApi(LifecycleProvider lifecycleProvider, Retrofit retrofit) {
        super(lifecycleProvider, retrofit);
    }


    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Observable getObservable() {
        BookService service = getRetrofit().create(BookService.class);
        return service.getBook(id);
    }
}
