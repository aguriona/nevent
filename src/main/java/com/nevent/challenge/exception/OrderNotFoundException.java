package com.nevent.challenge.exception;

import com.nevent.challenge.exception.handler.ErrorMessage;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String id) {
        super(ErrorMessage.ORDERNOTFOUND + id);
    }
}
