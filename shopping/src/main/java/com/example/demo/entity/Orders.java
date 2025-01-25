package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * orderテーブルを操作するためのクラス.
 * @author 大西竣介
 */
@SuppressWarnings("serial")
public class Orders implements Serializable {
	private String orderId;
	private String itemId;
	private String ornerId;
	private String purchaserId;
	private LocalDateTime orderDateTime;
	
	/**
	 * orderIdのgetter.
	 * @return orderId.
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * orderIdのsetter.
	 * @param orderId orderId.
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * itemIdのgetter.
	 * @return itemId.
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * itemIdのsetter.
	 * @param itemId itemId.
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * ornerIdのgerrer.
	 * @return ornerId.
	 */
	public String getOrnerId() {
		return ornerId;
	}
	/**
	 * ornerIdのsetter.
	 * @param ornerId ornerId.
	 */
	public void setOrnerId(String ornerId) {
		this.ornerId = ornerId;
	}
	
	/**
	 * purchaserIdのgetter.
	 * @return purchaserId.
	 */
	public String getPurchaserId() {
		return purchaserId;
	}
	/**
	 * purchaserIdのsetter.
	 * @param purchaserId purchaserId.
	 */
	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}
	
	/**
	 * orderDateTimeのgetter.
	 * @return orderDateTime.
	 */
	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}
	/**
	 * orderDateTimeのsetter.
	 * @param orderDateTime orderDateTime.
	 */
	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
}
