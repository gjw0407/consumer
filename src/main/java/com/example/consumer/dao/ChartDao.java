package com.example.consumer.dao;

import com.example.consumer.chartdata.ChartData;

public interface ChartDao {
    void addChartData(ChartData chartData);
    ChartData getChartData();
}
