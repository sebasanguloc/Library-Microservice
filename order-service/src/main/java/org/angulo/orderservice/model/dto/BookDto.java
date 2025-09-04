package org.angulo.orderservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {

    private String title;
    private String author;
    private Double price;
    private Integer stock;

}
