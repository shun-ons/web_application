package com.example.demo.exception;
/**
 * 在庫がない場合の例外を扱うクラス.
 * @author 夏木翔吾
 */
@SuppressWarnings("serial")
public class StockShortageException extends RuntimeException {
    public StockShortageException(String msg) {
        super(msg);
    }
}