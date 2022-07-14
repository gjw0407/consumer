package com.example.consumer.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user_chart_history")
public class Chart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userChartHistoryId;
    private int userId;
    private int keywordId;
    private int periodSec;
    private String startDate;
    private String endDate;

    @Builder
    public Chart(int keywordId,
                 int userId,
                 int periodSec,
                 String startDate,
                 String endDate) {
        this.keywordId = keywordId;
        this.userId = userId;
        this.periodSec = periodSec;
        this.startDate = startDate;
        this.endDate = endDate;

    }
}
