package me.dio.votacao.bbb.api.exception;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
