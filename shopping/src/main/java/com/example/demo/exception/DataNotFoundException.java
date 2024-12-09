package com.example.demo.exception;

@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String msg) {
        super(msg);
    }
}
