package com.scott.lib.callback;

/**
 * author: heshantao
 * data: 2017/1/21.
 * dataBinding 事件绑定 回调
 */

public interface DbindingEventCallback<T> {

    //带参数的点击
    public void onViewClick(T t);

    public void onViewClick();


    //带参数的长点击
    public void onViewLongClick(T t);

    public void onViewLongClick();

}
