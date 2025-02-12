package com.nevent.challenge.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderRequest(
        @NotBlank String client,
        @NotEmpty List<@Valid ItemRequest> items
) {}