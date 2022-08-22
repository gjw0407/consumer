package com.example.consumer.model;

import com.example.consumer.exception.InterceptorErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterceptorErrorResponse {
    private InterceptorErrorCode errorCode;
    private String errorMessage;
}
