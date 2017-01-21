package com.scott.lib.data.ben;

import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.scott.lib.callback.RealmDataBindCallback;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * author: heshantao
 * data: 2017/1/20.
 * 实体基础类使用Realm 和dataBind 需要特殊处理
 */

public class BaseBean extends RealmObject implements Observable, RealmDataBindCallback {
    int id;
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
