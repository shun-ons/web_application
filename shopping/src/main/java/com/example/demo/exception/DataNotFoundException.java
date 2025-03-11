package com.example.demo.exception;
/**
 * データが見つからなかった場合の例外を扱うクラス.
 * @author 夏木翔吾
 */
@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String msg) {
        super(msg);
    }
}
