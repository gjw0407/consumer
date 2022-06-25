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
    private int period_sec;
    private String start_date;
    private String end_date;

    public Chart toEntity() {
        return Chart.builder()
                .keywordId(keywordId)
                .userId(userId)
                .period_sec(period_sec)
                .start_date(start_date)
                .end_date(end_date)
                .build();
    }

}
