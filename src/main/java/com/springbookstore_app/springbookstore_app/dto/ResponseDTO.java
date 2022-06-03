package com.springbookstore_app.springbookstore_app.dto;

import com.springbookstore_app.springbookstore_app.model.UserRegistration;
import lombok.Data;

public class ResponseDTO {
    private String message;
    private Object data;
    public String token;

    public ResponseDTO(String message, Object data,String token) {
        this.message = message;
        this.data = data;
        this.token = token;
    }
    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;

    }


}
