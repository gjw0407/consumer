package com.example.consumer.exception;

import com.example.consumer.model.ConsumerErrorResponse;
import com.example.consumer.model.InterceptorErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(ConsumerException.class)
    public ConsumerErrorResponse handleConsumerException(
            ConsumerException e, HttpServletRequest request
    ){
        log.error("@ExceptionHandler(ConsumerException.class)");
        log.error("errorCode: {}, url: {}, message: {}",
                e.getConsumerErrorCode(), request.getRequestURI(), e.getDetailMessage());

        return ConsumerErrorResponse.builder()
                .errorCode(e.getConsumerErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

    @ExceptionHandler(InterceptorException.class)
    public InterceptorErrorResponse handleInterceptorException(
            InterceptorException e,
            HttpServletRequest request
    ){
        log.error("@ExceptionHandler(InterceptorException.class)");
        log.error("url: {}, message: {}",
                request.getRequestURI(), e.getMessage());

        return InterceptorErrorResponse.builder()
                .errorCode(e.getInterceptorErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ConsumerErrorResponse handleException(
            Exception e,
            HttpServletRequest request
    ){
        log.error("@ResponseStatus(code = HttpStatus.BAD_REQUEST)");
        log.error("url: {}, message: {}",
                request.getRequestURI(), e.getMessage());

        return ConsumerErrorResponse.builder()
                .errorCode(ConsumerErrorCode.INTERNAL_SERVER_ERROR)
                .errorMessage(ConsumerErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }
}
