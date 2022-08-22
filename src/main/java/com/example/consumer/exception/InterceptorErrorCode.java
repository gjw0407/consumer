package com.example.consumer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InterceptorErrorCode {

    // 만료된 토큰
    TOKEN_EXPIRED("만료된 토큰입니다"),
    
    // 토큰 갱신
    TOKEN_REFRESH("토큰 갱신이 필요합니다"), // 실제로 message : TOKEN으로 보낼거임

    // 토큰 비어있음
    TOKEN_EMPTY("토큰이 비어있습니다"),
    
    // 기타
    INVALID_REQUEST("잘못된 값이 요청되었습니다"),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다");


    private final String message;

}
