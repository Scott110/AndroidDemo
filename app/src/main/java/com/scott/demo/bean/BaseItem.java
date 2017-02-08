package com.scott.demo.bean;

import android.databinding.Observable;

import com.scott.lib.callback.RealmDataBindCallback;

import io.realm.RealmObject;

/**
 * author: heshantao
 * data: 2017/1/20.
 */

public class BaseItem implements RealmDataBindCallback {

    @Override
    public synchronized void notifyChange() {

    }

    public void notifyPropertyChanged(int fieldId) {

    }
}
