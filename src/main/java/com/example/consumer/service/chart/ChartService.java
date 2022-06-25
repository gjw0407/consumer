package com.example.consumer.service.chart;

import com.example.consumer.model.ChartDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ChartService {
    ResponseEntity<Map<String, Object>> addChart(ChartDto chartDto);
}
