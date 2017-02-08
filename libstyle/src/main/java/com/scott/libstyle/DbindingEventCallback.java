package com.scott.libstyle;

import android.view.View;

/**
 * author: heshantao
 * data: 2017/1/21.
 * dataBinding 事件绑定 回调
 */

public interface DbindingEventCallback<T> {

    //带参数的点击
    public void onViewClick(View view, T t);

    public void onViewClick(View view);


    //带参数的长点击
    public void onViewLongClick(View view, T t);

    public void onViewLongClick(View view);

}
