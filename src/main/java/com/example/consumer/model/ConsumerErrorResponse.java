package com.example.consumer.model;

import com.example.consumer.exception.ConsumerErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsumerErrorResponse {
    private ConsumerErrorCode errorCode;
    private String errorMessage;
}
