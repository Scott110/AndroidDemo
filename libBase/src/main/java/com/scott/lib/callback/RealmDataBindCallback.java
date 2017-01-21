package com.scott.lib.callback;

import io.realm.RealmChangeListener;

/**
 * author: heshantao
 * data: 2017/1/20.
 * realmDataBind 回调
 */

public interface RealmDataBindCallback {
    void notifyChange();

    void notifyPropertyChanged(int fieldId);

    interface Factory {
        RealmChangeListener create();
    }

    //用于数据更新
    RealmDataBindCallback.Factory FACTORY = new Factory() {
        @Override
        public RealmChangeListener create() {
            return new RealmChangeListener() {
                @Override
                public void onChange(Object element) {
                    if (element instanceof RealmDataBindCallback) {
                        ((RealmDataBindCallback) element).notifyChange();
                    }
                }
            };
        }
    };
}
