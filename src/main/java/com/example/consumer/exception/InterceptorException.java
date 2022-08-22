package com.example.consumer.exception;

import lombok.Getter;

@Getter
public class InterceptorException extends RuntimeException{
    private InterceptorErrorCode interceptorErrorCode;
    private String detailMessage;

    public InterceptorException(InterceptorErrorCode errorCode){
        super(errorCode.getMessage());
        this.interceptorErrorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }

    public InterceptorException(InterceptorErrorCode errorCode, String errorMessage){
        super(errorCode.getMessage());
        this.interceptorErrorCode = errorCode;
        this.detailMessage = errorMessage;
    }
}
