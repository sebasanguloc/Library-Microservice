package org.angulo.bookservice.Exceptions;

public class InsufficientBooksException extends RuntimeException {
    public InsufficientBooksException(String message) {
        super(message);
    }
}
