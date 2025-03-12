package com.example.demo.input;

import lombok.Data;

/**
 * ユーザの情報の入力を受け付けるクラス.
 * @author 夏木翔吾
 */
@Data
public class UserDetailForm {

	private String mailAddress;
	private String password;
	private String name;
	private int point;
}
