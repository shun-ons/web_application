package com.example.demo.input;

import lombok.Data;

/**
 * 入金情報を管理するモデルクラス
 * @author 夏木翔吾
 */
@Data
public class DepositInput {

    /** 入金額 */
    private int amount;

    /** ユーザーID */
    private String userId;
}
