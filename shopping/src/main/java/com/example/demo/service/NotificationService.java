package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Item;
import com.example.demo.entity.MUser;
import com.example.demo.entity.Notification;
import com.example.demo.entity.Orders;
import com.example.demo.repository.NotificationMapper;

import lombok.RequiredArgsConstructor;

/**
 * NotificationMapperクラスを呼び出して,notificationテーブルを操作するためのクラス.
 * @author 大西竣介
 */
@RequiredArgsConstructor
@Transactional
@Service
public class NotificationService {
	@Autowired
	private final NotificationMapper notificationMapper;
	@Autowired
	private final UserService userService;
	@Autowired
	private final ItemService itemService;
	@Autowired
	private final OrdersService ordersService;
	
	/**
	 * データベースから全ての通知を取得するためのメソッド.
	 * @return すべての通知.Notification型のオブジェクトのリスト.
	 */
	public List<Notification> selectAll() {
		return notificationMapper.selectAll();
	}
	
	/**
	 * notificationIdを用いて,テーブルから一つの通知を取得するためのメソッド.
	 * @param notificationId 取得したい通知のID.
	 * @return Notification型のオブジェクト.
	 */
	public Notification selectByNotificationId(String notificationId) {
		return notificationMapper.selectByNotificationId(notificationId);
	}
	
	/**
	 * ある出品者の通知を全て取得するためのメソッド.
	 * @param ornerId 取得したいユーザのuserId.
	 * @return あるユーザ宛の通知.Notification型のオブジェクトのリスト.
	 */
	public List<Notification> selectByOrnerId(String ornerId) {
		return notificationMapper.selectByOrnerId(ornerId);
	}
	
	/**
	 * 新たに通知をテーブルに挿入するためのメソッド.
	 * @param notification 新たに挿入したい通知のオブジェクト.
	 */
	public void addNotification(Map<String, String> allParams) {
		Notification notification = new Notification();
		// 通知IDを設定.
		notification.setNotificationId(UUID.randomUUID().toString());
		String content;
		String type = allParams.get("type");
		if (type.equals("call")) {
			String orderId = allParams.get("orderId");
			Orders orders = ordersService.getOrdersByOrderId(orderId);
			// 商品IDを設定.
			String itemId = orders.getItemId();
			notification.setItemId(itemId);
			// 販売者IDを設定.
			String ornerId = orders.getOrnerId();
			notification.setOrnerId(ornerId);
			// 購入者IDを設定.
			String purchaserId = orders.getPurchaserId();
			notification.setPurchaserId(purchaserId);
			// 通知内容を設定.
			Item item = itemService.selectById(itemId);
			String itemName = item.getItemName();
			MUser purchaser = userService.getUserOne(purchaserId);
			String purchaserName = purchaser.getName();
			content = purchaserName + "さんから「" + itemName + "」の購入依頼が届いています!確認してください!";
			
		} else {
			Notification call = this.selectByNotificationId(allParams.get("notificationId"));
			// 商品IDをセット.
			String itemId = call.getItemId();
			Item item = itemService.selectById(itemId);
			String itemName = item.getItemName();
			notification.setItemId(itemId);
			// 通知の受け取り側をセット.
			notification.setOrnerId(call.getPurchaserId());
			// 通知の送信者側をセット.
			String purchaserId = call.getOrnerId();
			notification.setPurchaserId(purchaserId);
			MUser purchaser = userService.getUserOne(purchaserId);
			String purchaserName = purchaser.getName();
			// 通知内容を設定.
			if (type.equals("notFind")) {
				content = purchaserName + "さんと「" + itemName + "」の受け取り日時の予定が合いませんでした。確認してください!";
			} else {
				content = purchaserName + "さんから「" + itemName + "」の受け取り日時の指定がされました!確認してください!";
			}
		}
		notification.setContent(content);
		// 通知のタイプを設定.
		notification.setType(type);

		// 通知を未読に設定.
		notification.setRead_(false);
		// 編集時間を設定.
		LocalDateTime now = LocalDateTime.now();
		notification.setDateTime_(now);
		
		notificationMapper.insert(notification);
	}
	
	/**
	 * 通知をテーブルから削除するためのメソッド.
	 * @param notificationId 削除したい通知のID.
	 */
	public void delete(String notificationId) {
		notificationMapper.delete(notificationId);
	}
}
