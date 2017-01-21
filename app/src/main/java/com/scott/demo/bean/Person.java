package com.scott.demo.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.scott.demo.BR;

import io.realm.RealmObject;

/**
 * Created by scott_he on 2016/11/10.
 */

public class Person extends BaseObservable {
    private String age;
    private String name;
    private String picUrl;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
