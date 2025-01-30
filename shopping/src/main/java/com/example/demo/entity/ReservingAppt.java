package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * 受け取り予約を行うreservingApptテーブルを操作するためのクラス.
 * @author 大西竣介
 */
@Getter
@Setter
public class ReservingAppt {
	private String reservingApptId;
	private String itemId;
	private String place1;
	private String date1;
	private String time1;
	private String place2;
	private String date2;
	private String time2;
	private String place3;
	private String date3;
	private String time3;
	private LocalDateTime reservingApptDateTime;
}
