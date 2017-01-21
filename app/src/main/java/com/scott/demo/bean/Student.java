package com.scott.demo.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


/**
 * author: heshantao
 * data: 2017/1/19.
 */

public class Student extends BaseObservable{
    private String Sname;
    private int age;


    @Bindable
    public String getSname() {
        return Sname;
    }

    public void setSname(String name) {
        this.Sname = name;
        notifyPropertyChanged(com.scott.demo.BR.sname);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
