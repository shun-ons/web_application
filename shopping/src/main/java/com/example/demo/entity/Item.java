package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class Item implements Serializable {
    private String itemId;
    private String itemName;
    private Integer itemPrice;
    private String ornerName;
    private String ornerId;
    private String message;
    private LocalDateTime salesDateTime;
    private boolean isSold = true;
    private boolean inCart = false;

    public String getItemId() {
        return this.itemId;
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
    
    public String getComment() {
    	return this.message;
    }
    
    public void setComment(String comment) {
    	this.message = comment;
    }
    
    public LocalDateTime getSalesDateTime() {
    	return this.salesDateTime;
    }
    
    public void setSalesDateTime(LocalDateTime salesDateTime) {
    	this.salesDateTime = salesDateTime;
    }
    
    public boolean getIsSold() {
    	return this.isSold;
    }
    
    public void setIsSold(boolean isSold) {
    	this.isSold = isSold;
    }
    
	public String getOrnerId() {
		return ornerId;
	}

	public void setOrnerId(String ornerId) {
		this.ornerId = ornerId;
	}

	public boolean getInCart() {
		return inCart;
	}

	public void setInCart(boolean inCart) {
		this.inCart = inCart;
	}

    @Override
    public String toString() {
        return "Item{id=" + itemId + ", name='" + itemName + "', price=" + itemPrice + "', ornerName='" + ornerName + "', ornerId='" + ornerId + "', comment='" + message + "', date='" + salesDateTime + "', isSold='" + "'}";
    }
}
