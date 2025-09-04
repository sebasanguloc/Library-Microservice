package org.angulo.orderservice.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.angulo.orderservice.constant.Status;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderDto {

    @NotBlank
    private String userEmail;

    @NotBlank
    private String status;

    private LocalDateTime createdAt;

}
