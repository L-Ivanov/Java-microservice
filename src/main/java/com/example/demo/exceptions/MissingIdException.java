package com.example.demo.exceptions;

public class MissingIdException extends RuntimeException {

    public MissingIdException(String e) {
        super(e);
    }
}
