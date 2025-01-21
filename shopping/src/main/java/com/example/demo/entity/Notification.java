package com.example.demo.entity;

import java.time.LocalDateTime;

/**
 * notificationテーブルからデータを取得,またはテーブルへデータを登録するためのクラス.
 * @author 大西竣介
 */
@SuppressWarnings("serial")
public class Notification {
	private String notificationId;  // 通知のID. notificationテーブルの主キー.
	private String itemId;          // 購入された商品のID.
	private String ornerId;         // 出品者のID.
	private String purchaserId;     // 購入者のID.
	private String content;         // 通知の内容. 定型文のつもりで作成.
	private LocalDateTime dateTime; // テーブルの編集日時.
	
	/**
	 * notificationIdのgetter.
	 * @return notificationId.
	 */
	public String getNotificationId() {
		return notificationId;
	}
	/**
	 * notificationIdのsetter.
	 * @param notificationId 登録したいnotificationId.
	 */
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	
	/**
	 * itemIdのgetter.
	 * @return itemId.
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * itemIdのsetter
	 * @param itemId 登録したいitemId.
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * ornerIdのgetter.
	 * @return ornerId.
	 */
	public String getOrnerId() {
		return ornerId;
	}
	/**
	 * ornerIdのsetter.
	 * @param ornerId 登録したいornerId.
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
	 * @param purchaserId 登録したいpurchaserId.
	 */
	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}
	
	/**
	 * conttentのgetter.
	 * @return content.
	 */
	public String getContent() {
		return content;
	}
	/**
	 * contentのsettter.
	 * @param content 登録したいcontent.
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * dateTimeのgetter
	 * @return dateTime.
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	/**
	 * dataTimeのsetter.
	 * @param dateTime 登録したいdataTime.
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}
