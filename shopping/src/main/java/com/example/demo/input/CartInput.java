package com.example.demo.input;

import java.io.Serializable;
import java.util.List;

/**
 * カートの入力情報を管理するクラス。
 * シリアライズ可能なデータとして扱う。
 * @author 石井叶輝
 */
@SuppressWarnings("serial")
public class CartInput implements Serializable {

    /** カート内の合計金額 */
    private Integer totalAmount;

    /** 請求金額 */
    private Integer billingAmount;

    /** カート内の商品リスト */
    private List<CartItemInput> cartItemInputs;

    /**
     * カート内の合計金額を取得する。
     * 
     * @return 合計金額
     */
    public Integer getTotalAmount() {
        return totalAmount;
    }

    /**
     * カート内の合計金額を設定する。
     * 
     * @param totalAmount 合計金額
     */
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 請求金額を取得する。
     * 
     * @return 請求金額
     */
    public Integer getBillingAmount() {
        return billingAmount;
    }

    /**
     * 請求金額を設定する。
     * 
     * @param billingAmount 請求金額
     */
    public void setBillingAmount(Integer billingAmount) {
        this.billingAmount = billingAmount;
    }

    /**
     * カート内の商品リストを取得する。
     * 
     * @return カート内の商品リスト
     */
    public List<CartItemInput> getCartItemInputs() {
        return cartItemInputs;
    }

    /**
     * カート内の商品リストを設定する。
     * 
     * @param cartItemInputs カート内の商品リスト
     */
    public void setCartItemInputs(List<CartItemInput> cartItemInputs) {
        this.cartItemInputs = cartItemInputs;
    }
}
