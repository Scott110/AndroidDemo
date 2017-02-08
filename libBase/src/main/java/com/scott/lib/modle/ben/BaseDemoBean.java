package com.scott.lib.modle.ben;


import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * author: heshantao
 * data: 2017/1/20.
 * 案例类 后面写实体类就按照这个样子写
 * 同时使用Realm 和dataBind 需要特殊处理 仿造dBinding 中的BaseObservable
 * 使用Realm 必须继承RealmObject 不能继承其他的
 */
/*
public class BaseDemoBean extends RealmObject implements Observable {
    private long _id;
    // FROM BASE OBSERVABLE
    @Ignore
    public transient PropertyChangeRegistry mCallbacks;


    @Override
    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        if (mCallbacks == null) {
            mCallbacks = new PropertyChangeRegistry();
        }
        mCallbacks.add(onPropertyChangedCallback);
    }

    @Override
    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        if (mCallbacks == null) {
            mCallbacks = new PropertyChangeRegistry();
        }
        mCallbacks.remove(onPropertyChangedCallback);
    }

    public synchronized void notifyChange() {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, 0, null);
        }

    }

    public void notifyPropertyChanged(int fieldId) {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, fieldId, null);
        }
    }
}
*/
