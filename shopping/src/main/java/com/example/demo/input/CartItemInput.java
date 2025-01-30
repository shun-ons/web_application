package com.example.demo.input;

import java.io.Serializable;

/**
 * カート内の商品情報を管理するクラス。
 * シリアライズ可能なデータとして扱う。
 * @author 石井叶輝
 */
@SuppressWarnings("serial")
public class CartItemInput implements Serializable {

    /** カートアイテムのID */
    private String id;

    /** 商品ID */
    private String productId;

    /** 商品名 */
    private String productName;

    /** 商品の価格 */
    private Integer productPrice;

    /** 商品の数量 */
    private Integer quantity;

    /**
     * カートアイテムのIDを取得する。
     * 
     * @return カートアイテムのID
     */
    public String getId() {
        return this.id;
    }

    /**
     * カートアイテムのIDを設定する。
     * 
     * @param id カートアイテムのID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 商品IDを取得する。
     * 
     * @return 商品ID
     */
    public String getProductId() {
        return this.productId;
    }

    /**
     * 商品IDを設定する。
     * 
     * @param productId 商品ID
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 商品名を取得する。
     * 
     * @return 商品名
     */
    public String getProductName() {
        return this.productName;
    }

    /**
     * 商品名を設定する。
     * 
     * @param productName 商品名
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 商品の価格を取得する。
     * 
     * @return 商品の価格
     */
    public Integer getProductPrice() {
        return this.productPrice;
    }

    /**
     * 商品の価格を設定する。
     * 
     * @param productPrice 商品の価格
     */
    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 商品の数量を取得する。
     * 
     * @return 商品の数量
     */
    public Integer getQuantity() {
        return this.quantity;
    }

    /**
     * 商品の数量を設定する。
     * 
     * @param quantity 商品の数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
