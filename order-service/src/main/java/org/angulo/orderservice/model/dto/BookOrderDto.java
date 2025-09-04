package org.angulo.orderservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookOrderDto {

    private String title;
    private Integer quantity;

}
