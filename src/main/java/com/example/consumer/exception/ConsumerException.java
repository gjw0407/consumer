package com.example.consumer.exception;

import lombok.Getter;

@Getter
public class ConsumerException extends RuntimeException{
    private ConsumerErrorCode consumerErrorCode;
    private String detailMessage;

    public ConsumerException(ConsumerErrorCode errorCode){
        super(errorCode.getMessage());
        this.consumerErrorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }

    public ConsumerException(ConsumerErrorCode errorCode, String errorMessage){
        super(errorCode.getMessage());
        this.consumerErrorCode = errorCode;
        this.detailMessage = errorMessage;
    }
}
