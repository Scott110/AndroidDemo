package com.scott.lib.dBinding;

import android.content.Context;
import android.view.View;

import com.scott.lib.callback.DbindingEventCallback;

/**
 * author: heshantao
 * data: 2017/1/21.
 * 绑定点击事件
 * 如果不带参数 xml 写法 handler::onClick
 * 带参数写法：(view)->onClick(view,obj)
 */

public class EventHandler<T> {
    private static final String TAG = EventHandler.class.getSimpleName();
    Context context;
    DbindingEventCallback<T> mCallback;

    public EventHandler(Context context) {
        this.context = context;
    }

    public void setEventCallback(DbindingEventCallback<T> callback) {
        this.mCallback = callback;
    }


    public void onClick(View view) {
        mCallback.onViewClick();
    }

    //必须是public 否则出错
    public void onClick(View view, T t) {
        mCallback.onViewClick(t);
    }

    public void onLongClick() {
        mCallback.onViewLongClick();
    }

    public void onLongClick(View view, T t) {
        mCallback.onViewLongClick(t);
    }
}
