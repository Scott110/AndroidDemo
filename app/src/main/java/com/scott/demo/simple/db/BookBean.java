package com.scott.demo.simple.db;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.scott.demo.BR;
import com.scott.lib.callback.RealmDataBindCallback;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * author: heshantao
 * data: 2017/2/7.
 */

public class BookBean extends RealmObject implements Observable {

    @PrimaryKey
    private long _id;
    private String bookName;

    @Bindable
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        if (!isManaged()) {
        notifyPropertyChanged(BR.bookName);
        }

    }

    @Bindable
    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
        if (!isManaged()) {
            notifyPropertyChanged(BR._id);
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
