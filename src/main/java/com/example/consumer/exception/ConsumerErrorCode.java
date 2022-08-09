package com.example.consumer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConsumerErrorCode {
    
    
    
    // 로그인
    LOGIN_ERROR("아이디 혹은 비밀번호가 잘못되었습니다"),
    
    // 회원가입
    REGISTER_DUPLICATE_EMAIL("중복되는 이메일 입니다"),
    
    // 차트
    CHART_NO_KEYWORD("키워드가 없습니다"),
    
    // 기타
    INVALID_REQUEST("잘못된 값이 요청되었습니다"),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다");


    private final String message;

}
