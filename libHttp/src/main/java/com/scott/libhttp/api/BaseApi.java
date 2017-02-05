package com.scott.libhttp.api;

import android.support.v7.app.AppCompatDialogFragment;

import com.scott.libhttp.IBaseView;
import com.scott.libhttp.ben.HttpResponseEntity;
import com.scott.libhttp.callback.HttpOnNextCallback;
import com.scott.libhttp.constant.Constants;
import com.scott.libhttp.exception.ApiExecption;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatDialogFragment;

import java.lang.ref.SoftReference;

import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;

/**
 * author: heshantao
 * data: 2017/1/17.
 * 请求统一封装类
 */

public abstract class BaseApi<T> implements Func1<HttpResponseEntity<T>, T> {
    private final String TAG = this.getClass().getSimpleName();
    //rx生命周期管理
    private SoftReference<LifecycleProvider> lifecycleProvider;
    /*回调*/
    private SoftReference<HttpOnNextCallback> callback;
    /*是否能取消加载框*/
    private boolean canCancelProgressDialog;
    /*是否显示加载框(用于提交任务的dialog)*/
    private boolean showProgressDialog;
    //加载对话框
    private AppCompatDialogFragment progressDialog;
    /*是否显示加载中View 页面初次进入的加载View*/
    private boolean showVaryLoadingView;
    /*各种状态View 切换的View接口*/
    private IBaseView mView;

    private Retrofit retrofit;

    public BaseApi(LifecycleProvider lifecycleProvider, Retrofit retrofit) {
        setLifeProvider(lifecycleProvider);
        setRetrofit(retrofit);
        setShowVaryLoadingView(false);
        setShowProgressDialog(false);
    }

    public boolean isCanCancelProgressDialog() {
        return canCancelProgressDialog;
    }

    public void setCanCancelProgressDialog(boolean canCancelProgressDialog) {
        this.canCancelProgressDialog = canCancelProgressDialog;
    }

    public boolean isShowProgressDialog() {
        return showProgressDialog;
    }

    public void setShowProgressDialog(boolean showProgressDialog) {
        this.showProgressDialog = showProgressDialog;
    }

    public boolean isShowVaryLoadingView() {
        return showVaryLoadingView;
    }

    public void setShowVaryLoadingView(boolean showVaryLoadingView) {
        this.showVaryLoadingView = showVaryLoadingView;
    }


    public SoftReference<HttpOnNextCallback> getCallback() {
        return callback;
    }

    public void setCallback(HttpOnNextCallback callback) {
        this.callback = new SoftReference<HttpOnNextCallback>(callback);
    }

    public IBaseView getmView() {
        return mView;
    }

    public void setmView(IBaseView mView) {
        this.mView = mView;
    }

    public AppCompatDialogFragment getProgressDialog() {
        return progressDialog;
    }

    public void setProgressDialog(AppCompatDialogFragment progressDialog) {
        this.progressDialog = progressDialog;
    }

    /*
        *
        * 获取Rx生命周期
        *
        */
    public LifecycleProvider getLifeProvider() {
        return lifecycleProvider.get();
    }

    public void setLifeProvider(LifecycleProvider provider) {
        this.lifecycleProvider = new SoftReference<LifecycleProvider>(provider);
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }


    public abstract Observable getObservable();

    @Override
    public T call(HttpResponseEntity<T> tHttpResponseEntity) {
        if (tHttpResponseEntity.getCode() != Constants.Http.HTTP_OK_STATUS) {
            try {
                throw new ApiExecption(tHttpResponseEntity.getCode(), tHttpResponseEntity.getMessage());
            } catch (ApiExecption apiExecption) {
                apiExecption.printStackTrace();
            }
        }
        return tHttpResponseEntity.getData();
    }
}
