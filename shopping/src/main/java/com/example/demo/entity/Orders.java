package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
/**
 * orderテーブルを操作するためのクラス.
 * @author 大西竣介
 */
@Data
@SuppressWarnings("serial")
public class Orders implements Serializable {
	private String orderId;
	private String itemId;
	private String ornerId;
	private String purchaserId;
	private LocalDateTime orderDateTime;
}
