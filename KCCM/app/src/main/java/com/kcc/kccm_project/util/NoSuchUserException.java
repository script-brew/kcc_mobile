package com.kcc.kccm_project.util;

public class NoSuchUserException extends RuntimeException {
    private static final long serialVersionUID = 8678785153197644664L;

    public NoSuchUserException(String message) {
        super(message);
    }
}
