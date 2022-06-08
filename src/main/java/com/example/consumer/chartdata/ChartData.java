package com.example.consumer.chartdata;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="search_keyword_detail")
public class ChartData {
    @Id
    private int keyword_detail_id;
    private int keyword_id;
    private String col_code;
    private String search_date;
    private int quantity;

}
