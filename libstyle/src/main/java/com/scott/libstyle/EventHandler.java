package com.scott.libstyle;

import android.content.Context;
import android.view.View;

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

    public EventHandler(Context context, DbindingEventCallback<T> callback) {
        this.context = context;
        mCallback = callback;
    }

    public void setEventCallback(DbindingEventCallback<T> callback) {
        this.mCallback = callback;
    }


    public void onClick(View view) {
        mCallback.onViewClick(view);
    }

    //必须是public 否则出错
    public void onClick(View view, T t) {
        mCallback.onViewClick(view, t);
    }

    public void onLongClick(View view) {
        mCallback.onViewLongClick(view);
    }

    public void onLongClick(View view, T t) {
        mCallback.onViewLongClick(view, t);
    }
}
