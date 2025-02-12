package com.nevent.challenge.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @NotBlank
    private String product;
    @Min(1)
    private int quantity;
    @Positive
    private BigDecimal unitPrice;
}
