package com.example.demo.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
/**
 * 出品する際に商品情報を保持するためのクラス.
 * @author 大西竣介.
 */
@Getter
@Setter
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
}
