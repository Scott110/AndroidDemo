package com.scott.libhttp;

/**
 * Created by scott_he on 16/10/17.
 */

public interface IBaseView<T> {
    //加载中
    public void showLoading();

    //网络错误
    public void showNetWorkErro();

    //错误
    public void showErro();

    //无数据
    public void showEmpty();

    //重试
    public void retry();

    //视图恢复
    public void restorView();

}
