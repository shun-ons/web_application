package com.example.demo.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Product implements Serializable {
    private String id;
    private String name;
    private Integer price;
    private String ornerName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getOrnerName() {
        return ornerName;
    }

    public void setOrnerName(String ornerName) {
        this.ornerName = ornerName;
    }
}
