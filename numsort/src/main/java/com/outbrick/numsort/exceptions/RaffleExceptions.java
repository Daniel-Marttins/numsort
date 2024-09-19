package com.outbrick.numsort.exceptions;

public class RaffleExceptions extends RuntimeException {

    public static class RaffleNotFound extends RuntimeException {
        public RaffleNotFound(String message) {
            super(message);
        }
    }

}
