package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 注文を管理するクラス.
 * @author 大西竣介
 */
@Data
@SuppressWarnings("serial")
public class OrderItem implements Serializable {
    private String orderId;
    private String itemId;
    private String purchaserId;
    private String ornerId;
    private Integer priceAtOrder;
    private LocalDateTime orderDateTime;
    
    public String toString() {
    	return "orderItem{orderId='" + this.orderId + "', itemId='" + this.itemId +
    			"', purchaserId='" + this.purchaserId +"', ornerId='" + this.ornerId +
    			"', priceAtOrder='" + this.priceAtOrder + "', orderDateTime" + this.orderDateTime + "'}";
    }
}

