package com.nevent.challenge.exception.handler;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse {
    private int status;
    private String message;
    private Object data;
}
