package com.outbrick.numsort.exceptions;

public class AccountExceptions extends RuntimeException{

    public static class AccountExistsException extends RuntimeException {
        public AccountExistsException(String message) {
            super(message);
        }
    }

    public static class AccountNotFoundException extends RuntimeException {
        public AccountNotFoundException(String message) {
            super(message);
        }
    }

}
