package com.scott.demo.api;

import android.util.Log;

import com.scott.demo.PersonService;
import com.scott.demo.bean.Person;
import com.scott.libhttp.api.BaseApi;
import com.trello.rxlifecycle.LifecycleProvider;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * author: heshantao
 * data: 2017/1/23.
 */

public class PersonApi extends BaseApi<Person> {
    String id;

    @Inject
    public PersonApi(LifecycleProvider lifecycleProvider, Retrofit retrofit) {
        super(lifecycleProvider, retrofit);
    }


    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Observable getObservable() {
        PersonService service = getRetrofit().create(PersonService.class);
        return service.getPerson(id);
    }
}
