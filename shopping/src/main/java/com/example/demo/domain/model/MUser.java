package com.example.demo.domain.model;

import lombok.Data;

/**
 * ユーザー情報を管理するモデルクラス
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
