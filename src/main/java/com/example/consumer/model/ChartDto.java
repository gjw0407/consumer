package com.example.consumer.model;

import com.example.consumer.entity.Chart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ChartDto {
    private int keywordId;
    private int userId;
    private int periodSec;
    private String startDate;
    private String endDate;

    public Chart toEntity() {
        return Chart.builder()
                .keywordId(keywordId)
                .userId(userId)
                .periodSec(periodSec)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

}
