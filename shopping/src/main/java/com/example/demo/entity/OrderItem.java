package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;


@SuppressWarnings("serial")
public class OrderItem implements Serializable {
    private String orderId;
    private String itemId;
    private String purchaserId;
    private String ornerId;
    private Integer priceAtOrder;
    private LocalDateTime orderDateTime;
    
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    
    public String getPurchaserId() {
    	return this.purchaserId;
    }

    public void setPurchaserId(String purchaserId) {
    	this.purchaserId = purchaserId;
    }

    public String getOrnerId() {
    	return this.ornerId;
    }
    
    public void setOrnerId(String ornerId) {
    	this.ornerId = ornerId;
    }
    
    public Integer getPriceAtOrder() {
        return priceAtOrder;
    }

    public void setPriceAtOrder(Integer priceAtOrder) {
        this.priceAtOrder = priceAtOrder;
    }
    
    public LocalDateTime getOrderDateTime() {
    	return this.orderDateTime;
    }
    
    public void setOrderDateTime(LocalDateTime orderDateTime) {
    	this.orderDateTime = orderDateTime;
    }
    
    public String toString() {
    	return "orderItem{orderId='" + this.orderId + "', itemId='" + this.itemId +
    			"', purchaserId='" + this.purchaserId +"', ornerId='" + this.ornerId +
    			"', priceAtOrder='" + this.priceAtOrder + "', orderDateTime" + this.orderDateTime + "'}";
    }
}

