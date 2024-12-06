package com.example.demo.exception;

@SuppressWarnings("serial")
public class StockShortageException extends RuntimeException {
    public StockShortageException(String msg) {
        super(msg);
    }
}