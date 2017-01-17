package com.scott.lib.widget.varyview;

import android.view.View;

/**
 * author: heshantao
 * data: 2017/1/17.
 * <p>
 * 加载中，无网络，加载错误,网络错误，无数据 等视图切换管理类
 */

public class VaryViewHelperController {
    private final String TAG = this.getClass().getSimpleName();
    /*
    页面切换类
    */

    IVaryViewHelper viewHelper;
    /**
     * 错误页面
     */
    private View mErrorView;
    /**
     * 正在加载页面
     */
    private View mLoadingView;
    /**
     * 数据为空的页面
     */
    private View mEmptyView;

    /**
     * 网路错误页面
     */
    private View mNetworkErrorView;

    private View.OnClickListener listener;


    public VaryViewHelperController(View view) {
        this(new VaryViewHelper(view));
    }

    public VaryViewHelperController(IVaryViewHelper helper) {
        super();
        this.viewHelper = helper;
    }


    public void setLoadingView(View view) {
        mLoadingView = view;
    }

    public void setErroView(View view) {
        mErrorView = view;
        mErrorView.setClickable(true);
        mErrorView.setOnClickListener(listener);
    }

    public void setEmptyView(View view) {
        mEmptyView = view;
    }

    public void setNetErroView(View view) {
        mNetworkErrorView = view;
        mNetworkErrorView.setClickable(true);
        mNetworkErrorView.setOnClickListener(listener);
    }


    public void setViewClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    public void showEmptyView() {
        viewHelper.showLayout(mEmptyView);
    }

    public void showErrorView() {
        viewHelper.showLayout(mErrorView);
    }

    public void showNetworkErrorView() {
        viewHelper.showLayout(mNetworkErrorView);
    }

    public void showLoadingView() {
        viewHelper.showLayout(mLoadingView);
    }

    public void showContentView() {
        viewHelper.restoreView();

    }


    public static class Builder {
        private View mErrorView;
        private View mNetworkErrorView;
        private View mLoadingView;
        private View mEmptyView;
        private View mContentView;
        private View.OnClickListener clickListener;

        public Builder() {
        }

        public Builder(View.OnClickListener clickListener, View mNetworkErrorView, View mLoadingView, View mErrorView, View mEmptyView, View mDataView) {
            this.clickListener = clickListener;
            this.mNetworkErrorView = mNetworkErrorView;
            this.mLoadingView = mLoadingView;
            this.mErrorView = mErrorView;
            this.mEmptyView = mEmptyView;
            this.mContentView = mDataView;
        }

        public Builder setErrorView(View errorView) {
            mErrorView = errorView;
            return this;
        }

        public Builder setLoadingView(View loadingView) {
            mLoadingView = loadingView;
            return this;
        }

        public Builder setEmptyView(View emptyView) {
            mEmptyView = emptyView;
            return this;
        }

        public Builder setContentView(View dataView) {
            mContentView = dataView;
            return this;
        }

        public Builder setViewClickListener(View.OnClickListener listener) {
            clickListener = listener;
            return this;
        }

        public Builder setNetworkErrorView(View mNetworkErrorView) {
            this.mNetworkErrorView = mNetworkErrorView;
            return this;
        }

        public VaryViewHelperController builder() {
            VaryViewHelperController controller = new VaryViewHelperController(mContentView);
            controller.setEmptyView(mEmptyView);
            controller.setNetErroView(mNetworkErrorView);
            controller.setErroView(mErrorView);
            controller.setViewClickListener(clickListener);
            controller.setLoadingView(mLoadingView);
            return controller;
        }
    }
}
