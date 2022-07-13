package com.example.consumer.model;

import com.example.consumer.entity.Chart;
import com.example.consumer.entity.Keyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class KeywordDto {
    private String keyword;

    public Keyword toEntity() {
        return Keyword.builder()
                .keyword(keyword)
                .build();
    }
}
