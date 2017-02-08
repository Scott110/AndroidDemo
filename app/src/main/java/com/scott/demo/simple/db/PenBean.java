package com.scott.demo.simple.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * author: heshantao
 * data: 2017/2/6.
 */

public class PenBean extends RealmObject  {
    @PrimaryKey
    private int id;
    private String name;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
