package com.example.consumer.controller;

import com.example.consumer.chartdata.ChartData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import java.util.Random;

@RestController
public class ChartController {
    @GetMapping("/chart-data")
    public Object pieChartController() {
        ChartData sample = new ChartData();
        Map<String, Integer> temp = new HashMap<>();

        Random rand = new Random();
        int upperbound = 43;
        temp.put("In-Favor", rand.nextInt(upperbound));
        temp.put("Not In-Favor", rand.nextInt(upperbound));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        sample.setData(temp);
        sample.setDate(dtf.format(now));
        return sample;
    }
}
