package com.scott.lib.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scott.lib.base.mvvm.IBaseView;
import com.scott.lib.widget.varyview.DefaultEmptyView;
import com.scott.lib.widget.varyview.DefaultErroView;
import com.scott.lib.widget.varyview.DefaultLoadingView;
import com.scott.lib.widget.varyview.DefaultNetErroView;
import com.scott.lib.widget.varyview.VaryViewHelperController;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by scott_he on 16/10/17.
 */

public abstract class BaseFragment extends RxFragment implements IBaseView {
    public final String TAG = this.getClass().getSimpleName();
    Unbinder unBinder;
    VaryViewHelperController mController;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(savedInstanceState);
    }

    //获得容器布局文件
    public abstract int getLayoutId();

    //初始化
    public abstract void init(Bundle savedInstanceState);

    //懒加载时候调用
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }


    //初始化各种状态切换View
    void initVaryView(View contenView) {
        mController = new VaryViewHelperController
                .Builder().setContentView(contenView)
                .setLoadingView(new DefaultLoadingView(_mActivity))
                .setEmptyView(new DefaultEmptyView(_mActivity))
                .setErrorView(new DefaultErroView(_mActivity))
                .setNetworkErrorView(new DefaultNetErroView(_mActivity))
                .setViewClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        retry();
                    }
                }).builder();
    }


    public void setEmptyView(View view) {
        if (mController == null)
            return;
        mController.setEmptyView(view);
    }

    public void setErrorView(View view) {
        if (mController == null)
            return;
        mController.setErroView(view);
    }

    public void setNetErrorView(View view) {
        if (mController == null)
            return;
        mController.setNetErroView(view);
    }

    public void setLodingView(View view) {
        if (mController == null)
            return;
        mController.setLoadingView(view);
    }

    @Override
    public void showLoading() {
        if (mController == null)
            return;
        mController.showLoadingView();
    }

    @Override
    public void showEmpty() {
        if (mController == null)
            return;
        mController.showEmptyView();
    }

    @Override
    public void showNetWorkErro() {
        if (mController == null)
            return;
        mController.showNetworkErrorView();
    }

    @Override
    public void showErro() {
        if (mController == null)
            return;
        mController.showErrorView();
    }

    @Override
    public void restorView() {
        if (mController == null)
            return;
        mController.showContentView();
    }

    @Override
    public void retry() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unBinder = null;
    }
}
