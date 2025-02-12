package com.nevent.challenge.exception.handler;

import com.nevent.challenge.exception.InvalidOrderStatusException;
import com.nevent.challenge.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse handleOrderNotFoundException(OrderNotFoundException ex) {
        return buildApiResponse(HttpStatus.NOT_FOUND, ErrorMessage.ORDERNOTFOUND, ex.getMessage());
    }
    @ExceptionHandler(InvalidOrderStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleInvalidStatusException(InvalidOrderStatusException ex) {
        return buildApiResponse(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_STATUS, ex.getMessage());
    }
    private ApiResponse buildApiResponse(HttpStatus status, String message, String data) {
        return ApiResponse.builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();
    }
}
