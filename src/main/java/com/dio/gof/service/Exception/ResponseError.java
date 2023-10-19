package com.dio.gof.service.Exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class ResponseError {
    private LocalDateTime timestamp;
    private String status;
    private Integer statusCode;
    private String error;

    public ResponseError() {
        timestamp = LocalDateTime.now();
        status = "error";
        statusCode = 400;
        error = "";
    }
}
