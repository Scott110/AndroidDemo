package com.scott.demo.api;

import com.scott.demo.PersonService;
import com.scott.demo.bean.Person;
import com.scott.libhttp.api.BaseApi;
import com.scott.libhttp.callback.HttpOnNextCallback;
import com.trello.rxlifecycle.LifecycleProvider;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * author: heshantao
 * data: 2017/1/23.
 */

public class PersonApi extends BaseApi<Person> {
    String id;

    public PersonApi(HttpOnNextCallback mCallback, LifecycleProvider lifecycleProvider) {
        super(mCallback, lifecycleProvider);
    }


    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        PersonService service = retrofit.create(PersonService.class);
        return service.getPerson(id);
    }
}
