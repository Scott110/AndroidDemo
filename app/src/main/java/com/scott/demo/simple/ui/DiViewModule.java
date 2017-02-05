package com.scott.demo.simple.ui;

import android.util.Log;

import com.scott.demo.bean.Person;
import com.scott.lib.base.mvvm.IBaseView;
import com.scott.libhttp.api.BaseApi;
import com.scott.libhttp.callback.HttpOnNextCallback;
import com.scott.libhttp.manager.HttpManager;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * author: heshantao
 * data: 2017/2/5.
 */

public class DiViewModule extends DiVMContract.ViewModule {
    private static final String TAG = DiViewModule.class.getSimpleName();

    private HttpManager httpManager;

    @Inject
    public DiViewModule(HttpManager httpManager, IBaseView view) {
        super(view);
        this.httpManager = httpManager;
    }


    @Override
    public void requestPersonInfo(BaseApi api) {
        api.setCallback(callback);
        httpManager.doHttpDeal(api);
    }


    HttpOnNextCallback<List<Person>> callback = new HttpOnNextCallback<List<Person>>() {
        @Override
        public void onNext(List list) {
            Log.d(TAG, "onNext: 获得结果忘了请求");
            getControllerView().updateUI();

        }

    };


}
