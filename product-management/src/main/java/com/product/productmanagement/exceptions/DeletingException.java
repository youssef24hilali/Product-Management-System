package com.product.productmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DeletingException extends RuntimeException {
    public DeletingException(String message) {
        super(message);
    }
}
