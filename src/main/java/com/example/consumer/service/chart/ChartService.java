package com.example.consumer.service.chart;

import com.example.consumer.entity.Chart;
import com.example.consumer.model.ChartDto;
import com.example.consumer.model.ChartDtoKeyword;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ChartService {
    ResponseEntity<Map<String, Object>> addChart(ChartDto chartDto);

    List<ChartDtoKeyword> loadChart(int userId);
}
