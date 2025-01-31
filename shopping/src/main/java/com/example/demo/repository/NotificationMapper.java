package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Notification;

/**
 * notificationテーブルを操作するためのクラス.
 * @author 大西竣介
 */
@Mapper
@Repository
public interface NotificationMapper {
	/**
	 * データベースから全ての通知を取得するためのメソッド.
	 * @return すべての通知.Notification型のオブジェクトのリスト.
	 */
	@Select("SELECT * FROM notification")
	public List<Notification> selectAll();
	
	/**
	 * notificationIdを用いて,テーブルから一つの通知を取得するためのメソッド.
	 * @param notificationId 取得したい通知のID.
	 * @return Notification型のオブジェクト.
	 */
	@Select("SELECT * FROM notification WHERE notificationId = #{notificationId}")
	public Notification selectByNotificationId(String notificationId);
	
	/**
	 * ある出品者の通知を全て取得するためのメソッド.
	 * @param ornerId 取得したいユーザのuserId.
	 * @return あるユーザ宛の通知.Notification型のオブジェクトのリスト.
	 */
	@Select("SELECT * FROM notification WHERE ornerId = #{ornerId}")
	public List<Notification> selectByOrnerId(String ornerId);
	
	/**
	 * 新たに通知をテーブルに挿入するためのメソッド.
	 * @param notification 新たに挿入したい通知のオブジェクト.
	 * @return 処理対応件数.
	 */
	@Insert({"INSERT INTO notification(notificationId, itemId, ornerId, purchaserId, content, type, read_, dateTime_)",
		"VALUES(#{notificationId}, #{itemId}, #{ornerId}, #{purchaserId}, #{content}, #{type}, #{read_}, #{dateTime_})"})
	public int insert(Notification notification);
	
	/**
	 * 通知をテーブルから削除するためのメソッド.
	 * @param notificationId 削除したい通知のID.
	 * @return 処理対応件数.
	 */
	@Delete("DELETE FROM notification WHERE notificationId = #{notificationId}")
	public int delete(String notificationId);
}
