package org.angulo.orderservice.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderItemDto {

    @NotNull
    private Long orderId;

    @NotBlank
    private String bookTitle;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

}
