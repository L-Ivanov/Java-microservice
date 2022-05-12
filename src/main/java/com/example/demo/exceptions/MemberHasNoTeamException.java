package com.example.demo.exceptions;

public class MemberHasNoTeamException extends RuntimeException {
    public MemberHasNoTeamException(String e) {
        super(e);
    }
}
