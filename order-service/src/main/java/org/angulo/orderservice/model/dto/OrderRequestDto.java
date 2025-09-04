package org.angulo.orderservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequestDto {

    private String userEmail;
    private List<BookOrderDto> items;

}
