package com.example.consumer.controller;

import com.example.consumer.entity.ChartData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import java.util.Random;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ChartController {
    @GetMapping("/chart-data")
    public Object pieChartController() {
        ChartData sample = new ChartData();
        Map<String, Integer> temp = new HashMap<>();

        Random rand = new Random();
        int upperbound = 43;
        temp.put("Up", rand.nextInt(upperbound));
        temp.put("Down", rand.nextInt(upperbound));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return 1;
    }
}
