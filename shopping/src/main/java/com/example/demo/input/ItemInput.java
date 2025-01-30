package com.example.demo.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
/**
 * 出品する際に商品情報を保持するためのクラス.
 * @author 大西竣介.
 */
public class ItemInput {
	@NotBlank
	private String itemName;
	@NotNull
	private int itemPrice;
	@Size(min=0, max=50)
	private String comment;
	private String itemId;  // 更新用.
	
	public ItemInput(String itemName, int itemPrice, String comment) {
		this.setItemName(itemName);
		this.setItemPrice(itemPrice);
		this.setComment(comment);
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public int getItemPrice() {
		return this.itemPrice;
	}
	
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
}
