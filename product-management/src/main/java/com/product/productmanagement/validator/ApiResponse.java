package com.product.productmanagement.validator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse {

    private final boolean success;
    private final String msg;

    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }

}
