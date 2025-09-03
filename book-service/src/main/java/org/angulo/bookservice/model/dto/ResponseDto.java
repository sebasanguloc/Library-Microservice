package org.angulo.bookservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseDto<T> {
    private HttpStatus httpStatus;
    private String message;
    private T data;
}
