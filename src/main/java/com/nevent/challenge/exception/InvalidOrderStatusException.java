package com.nevent.challenge.exception;

import com.nevent.challenge.exception.handler.ErrorMessage;

public class InvalidOrderStatusException extends RuntimeException {
    public InvalidOrderStatusException(String status) {
        super(ErrorMessage.INVALID_STATUS + status);
    }
}
