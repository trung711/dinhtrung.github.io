package com.example.demo.dto.response;

import lombok.Data;

@Data
public class ResponseError {
    
    private int code;
    private String message;
}
