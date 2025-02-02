package com.example.demo.domain.model;

import lombok.Data;

/**
 * 入金情報を管理するモデルクラス
 */
@Data
public class DepositInput {

    /** 入金額 */
    private int amount;

    /** ユーザーID */
    private String userId;
}
