package com.scott.lib.db;


import com.scott.libhttp.api.BaseApi;
import com.scott.libhttp.manager.HttpManager;
import com.scott.libhttp.subscriber.RxSubscriber;

import rx.Observable;

/**
 * author: heshantao
 * data: 2017/2/7.
 */

public class Repository {

    private DbHelper dbHelper;
    private HttpManager httpManager;

    public Repository(DbHelper mDbHelper, HttpManager mHttpManager) {
        this.dbHelper = mDbHelper;
        this.httpManager = mHttpManager;

    }

    public void requestData(Class clazz, BaseApi api) {
        RxSubscriber subscriber = new RxSubscriber(api);
        Observable dbObservable = dbHelper.rxQueryRealmObjects(clazz);
        Observable netObservable = httpManager.httpDeal(api);
        Observable.amb(dbObservable, netObservable).subscribe(subscriber);
    }

}
