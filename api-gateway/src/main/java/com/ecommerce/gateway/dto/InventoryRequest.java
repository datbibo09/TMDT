package com.ecommerce.gateway.dto;

import com.ecommerce.inventory.domain.model.LocationCode;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InventoryRequest(
        @NotBlank(message = "Mã tồn kho không được trống")
        String inventoryCode,

        @NotNull(message = "Product ID không được trống")
        Long productId,

        @NotNull(message = "Kho không được trống")
        LocationCode location,

        @Min(value = 0, message = "Số lượng phải >= 0")
        int quantity
) {}
