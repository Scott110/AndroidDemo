package com.scott.demo.bean;

import io.realm.RealmObject;

/**
 * Created by scott_he on 2016/11/10.
 */

public class Person extends RealmObject {
    private String age;
    private String name;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
