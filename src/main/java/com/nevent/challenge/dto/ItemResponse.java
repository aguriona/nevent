package com.nevent.challenge.dto;

import java.math.BigDecimal;

public record ItemResponse(
        String product,
        int quantity,
        BigDecimal unitPrice
) {}
