package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * notificationテーブルからデータを取得,またはテーブルへデータを登録するためのクラス.
 * @author 大西竣介
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class Notification {
	private String notificationId;  // 通知のID. notificationテーブルの主キー.
	private String itemId;          // 購入された商品のID.
	private String ornerId;         // 出品者のID.
	private String purchaserId;     // 購入者のID.
	private String content;         // 通知の内容.
	private String type;            // 送信通知か返信通知か.
	private boolean read;           // falseならば未読, trueなら既読.
	private LocalDateTime dateTime; // テーブルの編集日時.
}
