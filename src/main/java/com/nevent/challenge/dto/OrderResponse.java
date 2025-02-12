package com.nevent.challenge.dto;

import com.nevent.challenge.entity.Status;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        String id,
        String client,
        LocalDateTime createdAt,
        Status status,
        List<ItemResponse> items
) {}
