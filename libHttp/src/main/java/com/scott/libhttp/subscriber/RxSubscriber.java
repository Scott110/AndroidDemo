package com.scott.libhttp.subscriber;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;

import com.scott.libhttp.IBaseView;
import com.scott.libhttp.api.BaseApi;
import com.scott.libhttp.callback.HttpOnNextCallback;
import com.scott.libhttp.manager.ExecptionManager;
import com.scott.libhttp.manager.HttpDialogManager;
import com.trello.rxlifecycle.LifecycleProvider;

import java.lang.ref.SoftReference;

import rx.Subscriber;


/**
 * author: heshantao
 * data: 2017/1/17.
 * Subscriber 封装实现类
 */

public class RxSubscriber<T> extends Subscriber<T> {
    /*是否弹框*/
    private boolean showPorgressDialog = false;
    /*是否显示LoadingView*/
    private boolean showVaryLoadingView = false;
    /* 软引用回调接口*/
    private SoftReference<HttpOnNextCallback> mSubscriberOnNextListener;
    /*软引用反正内存泄露*/
    private SoftReference<LifecycleProvider> lifeProvide;
    /*加载框可自己定义*/
    private AppCompatDialogFragment progressDialog;
    private IBaseView baseView;
    /*请求数据*/
    private BaseApi mApi;
    HttpDialogManager dialogManager;

    public RxSubscriber(BaseApi api) {
        this.mApi = api;
        this.mSubscriberOnNextListener = api.getCallback();
        this.lifeProvide = new SoftReference<>(api.getLifeProvider());
        baseView = mApi.getmView();
        initHttpDialogManager();
        progressDialog = mApi.getProgressDialog();
        setShowPorgress(api.isShowProgressDialog());
    }


    private void initHttpDialogManager() {
        if (lifeProvide.get() != null) {
            FragmentManager manager = null;
            if (lifeProvide.get() instanceof Fragment) {
                manager = ((Fragment) lifeProvide.get()).getChildFragmentManager();
            } else if (lifeProvide.get() instanceof AppCompatActivity) {
                manager = ((AppCompatActivity) lifeProvide.get()).getSupportFragmentManager();
            }
            if (manager != null) {
                dialogManager = new HttpDialogManager(manager);
            }
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }


    public boolean isShowPorgress() {
        return showPorgressDialog;
    }


    public boolean isShowLoadingView() {
        return showVaryLoadingView;
    }

    /**
     * 是否需要弹框设置
     *
     * @param showPorgress
     */
    public void setShowPorgress(boolean showPorgress) {
        this.showPorgressDialog = showPorgress;
    }


    /**
     * 是否需要loadingView
     *
     * @param showLoading
     */
    public void setShowLoadingView(boolean showLoading) {
        this.showVaryLoadingView = showLoading;
    }


    private void showVaryLoadingView() {
        if (!isShowLoadingView() || baseView == null) return;
        baseView.showLoading();
    }


    private void showVaryRestorView() {
        if (!isShowLoadingView() || baseView == null) return;
        baseView.restorView();
    }


    private void showErroView() {
        if (!isShowLoadingView() || baseView == null) return;
        baseView.showErro();
    }


    private void showNetErroView() {
        if (!isShowLoadingView() || baseView == null) return;
        baseView.showNetWorkErro();
    }

    /**
     * 显示加载框
     */
    private void showProgressDialog() {
        if (!isShowPorgress() || dialogManager == null || progressDialog == null) return;
        dialogManager.showLoadingDialog(progressDialog);

    }

    /**
     * 隐藏加载框
     */
    private void dismissProgressDialog() {
        if (!isShowPorgress() || dialogManager == null || dialogManager == null) return;
        dialogManager.dissLoadingDialog();
    }

    private void dismissDialog() {
        dismissProgressDialog();
        showVaryRestorView();
    }


    @Override
    public void onStart() {
        showProgressDialog();
        showVaryLoadingView();
    }

    @Override
    public void onCompleted() {
        dismissDialog();
    }

    @Override
    public void onError(Throwable e) {
        dismissDialog();
        doErro(e);
    }

    /*错误统一处理*/
    private void doErro(Throwable e) {
        ExecptionManager manager = ExecptionManager.getInstance();
        if (manager.isNetException(e)) {
            showNetErroView();
        } else if (manager.isApiException(e)) {
            showErroView();
        } else if (manager.isHttpException(e)) {
            showNetErroView();
        } else {
            showErroView();
        }

        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onError(e);
        }
    }

    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onNext(t);
        }
    }
}
