package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 商品の情報を管理するクラス.
 * @author 大西竣介
 */
@Data
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
    private boolean isCompletion = false; // 受け取り確認フラグを追加

    @Override
    public String toString() {
        return "Item{id=" + itemId + 
                ", name='" + itemName + 
                "', price=" + itemPrice + 
                "', ornerName='" + ornerName + 
                "', ornerId='" + ornerId + 
                "', comment='" + message + 
                "', date='" + salesDateTime + 
                "', isSold=" + isSold + 
                ", inCart=" + inCart + 
                ", isCompletion=" + isCompletion + "}";
    }
}
