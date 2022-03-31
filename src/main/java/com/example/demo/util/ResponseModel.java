package com.example.demo.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public final class ResponseModel {

    private HttpStatus httpStatus;
    private Integer status;
    private String message;
    private Object data;

}
