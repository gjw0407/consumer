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
    private int user_chart_historyId;
    private int userId;
    private int keywordId;
    private int period_sec;
    private String start_date;
    private String end_date;

    @Builder
    public Chart(int keywordId,
                 int userId,
                 int period_sec,
                 String start_date,
                 String end_date) {
        this.keywordId = keywordId;
        this.userId = userId;
        this.period_sec = period_sec;
        this.start_date = start_date;
        this.end_date = end_date;

    }
}
