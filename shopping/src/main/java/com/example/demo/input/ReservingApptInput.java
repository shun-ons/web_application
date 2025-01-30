package com.example.demo.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 受け取り予約を保持するためのクラス.
 * @author 大西竣介
 */
@Getter
@Setter
public class ReservingApptInput {
	@NotBlank
	private String itemId;
	@NotBlank
	private String place1;  // 第一希望の受け取り場所.
	@NotBlank
	private String date1;   // 第一希望の受け取り日.
	@NotBlank
	private String time1;   // 第一希望の受け取り時間.
	@NotBlank
	private String place2;  // 第二希望の受け取り場所.
	@NotBlank
	private String date2;   // 第二希望の受け取り日.
	@NotBlank
	private String time2;   // 第二希望の受け取り時間.
	@NotBlank
	private String place3;  // 第三希望の受け取り場所.
	@NotBlank
	private String date3;   // 第三希望の受け取り日.
	@NotBlank
	private String time3;   // 第三希望の受け取り時間.
}
