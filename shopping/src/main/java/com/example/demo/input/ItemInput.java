package com.example.demo.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ItemInput {
	@NotBlank
	private String itemName;
	@NotNull
	private int itemPrice;
	@NotBlank
	private String ornerName;
	@Size(min=0, max=50)
	private String comment;
	
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
	
	public String getOrnerName() {
		return this.ornerName;
	}
	
	public void setOrnerName(String ornerName) {
		this.ornerName = ornerName;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
}
