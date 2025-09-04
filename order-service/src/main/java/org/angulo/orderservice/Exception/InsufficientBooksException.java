package org.angulo.orderservice.Exception;

public class InsufficientBooksException extends RuntimeException {
    public InsufficientBooksException(String message) {
        super(message);
    }
}
