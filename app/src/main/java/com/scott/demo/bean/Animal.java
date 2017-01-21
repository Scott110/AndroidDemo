package com.scott.demo.bean;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * author: heshantao
 * data: 2017/1/20.
 */

public class Animal {
    public ObservableField<String> category = new ObservableField<String>();
    public ObservableInt age = new ObservableInt();
    public ObservableBoolean man = new ObservableBoolean();
}
