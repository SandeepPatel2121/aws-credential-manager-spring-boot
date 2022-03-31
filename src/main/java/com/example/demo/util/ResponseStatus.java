package com.example.demo.util;

import org.springframework.http.HttpStatus;

public final class ResponseStatus {

    public static ResponseModel create(String message, Object data, HttpStatus httpStatus, Integer status) {
        ResponseModel responseModel = new ResponseModel();

        responseModel.setMessage(message);
        responseModel.setData(data);
        responseModel.setHttpStatus(httpStatus);
        responseModel.setStatus(status);

        return responseModel;
    }

}
