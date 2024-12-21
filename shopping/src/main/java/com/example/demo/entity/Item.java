package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class Item implements Serializable {
    private String itemId;
    private String itemName;
    private Integer itemPrice;
    private String ornerName;
    private LocalDateTime salesDateTime;

    public String getitemId() {
        return itemId;
    }

    public void setId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getOrnerName() {
        return ornerName;
    }

    public void setOrnerName(String ornerName) {
        this.ornerName = ornerName;
    }
    
    public LocalDateTime getSalesDateTime() {
    	return this.salesDateTime;
    }
    
    public void setSalesDateTime(LocalDateTime salesDateTime) {
    	this.salesDateTime = salesDateTime;
    }
}
