package org.angulo.userservice.Exception;

import org.angulo.userservice.model.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDto<?>> handleUserNotFound(UserNotFoundException ex) {
        ResponseDto<?> response = new ResponseDto<>(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                null
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<?>> handleGeneric(Exception ex) {
        ResponseDto<ResponseDto<?>> response = new ResponseDto<>(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error",
                null
        );
        return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
