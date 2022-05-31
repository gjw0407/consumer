package com.example.consumer.chartdata;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import java.util.Map;

@Getter
@Setter
@ToString
public class ChartData {
    private Map data;
    private String date;
}
