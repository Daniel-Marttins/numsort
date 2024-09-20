package com.outbrick.numsort.exceptions;

public class HoldingException extends RuntimeException{

    public static class HoldingNotFoundException extends RuntimeException {
        public HoldingNotFoundException(String message) {
            super(message);
        }
    }

}
