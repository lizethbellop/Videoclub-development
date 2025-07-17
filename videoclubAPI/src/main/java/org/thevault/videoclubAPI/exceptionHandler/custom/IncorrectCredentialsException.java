package org.thevault.videoclubAPI.exceptionHandler.custom;

public class IncorrectCredentialsException extends RuntimeException {
    public IncorrectCredentialsException(String message) {
        super(message);
    }
}
