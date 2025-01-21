package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;
import com.example.demo.entity.Item;
import com.example.demo.entity.Notification;
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
	public void insert(String itemId, String ornerId, String purchaserId) {
		Notification notification = new Notification();
		notification.setItemId(itemId);
		notification.setOrnerId(ornerId);
		notification.setPurchaserId(purchaserId);
		Item item = itemService.selectById(itemId);
		String itemName = item.getItemName();
		MUser purchaser = userService.getUserOne(purchaserId);
		String purchaserName = purchaser.getName();
		String content = purchaserName + "さんが" + itemName + "を購入しました!";
		notification.setContent(content);
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
