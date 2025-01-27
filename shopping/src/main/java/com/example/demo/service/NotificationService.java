package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;
import com.example.demo.entity.Item;
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
	public void addNotification(String orderId) {
		Notification notification = new Notification();
		// 通知IDを設定.
		notification.setNotificationId(UUID.randomUUID().toString());
		
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
		String content = purchaserName + "さんが" + itemName + "を購入しました!";
		notification.setContent(content);
		// 編集時間を設定.
		LocalDateTime now = LocalDateTime.now();
		notification.setDateTime(now);
		
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
