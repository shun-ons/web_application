package com.example.demo.entity;

import lombok.Data;

/**
 * ユーザー情報を管理するモデルクラス
 * @author 夏木翔吾
 */
@Data
public class MUser {

    /** ユーザーID */
    private String userId;

    /** メールアドレス */
    private String mailAddress;

    /** パスワード */
    private String password;

    /** ユーザー名 */
    private String name;

    /** ユーザーの権限 */
    private String role;

    /** ユーザーのポイント */
    private int point;
}
