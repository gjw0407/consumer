package com.example.consumer.dao;

import com.example.consumer.chartdata.ChartData;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BinaryChartDao implements ChartDao {
    //TODO Replace Hashmap to DB
    private HashMap<String, Integer> dbMap;

    @Override
    public void addChartData(ChartData chartData) {
        Map<String, Integer> map = chartData.getData();
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            dbMap.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public ChartData getChartData() {
        //TODO Get Latest Data from DB
        ChartData latestData = new ChartData();
        return latestData;
    }

}
