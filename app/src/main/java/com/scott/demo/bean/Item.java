package com.scott.demo.bean;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.scott.demo.BR;
import com.scott.lib.callback.RealmDataBindCallback;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * author: heshantao
 * data: 2017/1/20.
 * <p>
 * 自定义Observable 和 BaseObservable 功能一样
 */

public class Item extends RealmObject implements Observable, RealmDataBindCallback {
    String qq;

    @Bindable
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
        if (!isManaged()) {
            notifyPropertyChanged(BR.qq);
        }
    }

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

    @Override
    public synchronized void notifyChange() {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, 0, null);
        }

    }

    @Override
    public void notifyPropertyChanged(int fieldId) {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, fieldId, null);
        }
    }


}
