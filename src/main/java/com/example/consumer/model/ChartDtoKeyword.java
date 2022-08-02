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
public class ChartDtoKeyword {
    private String keyword;
    private int userId;
    private int periodSec;
    private String startDate;
    private String endDate;

}
