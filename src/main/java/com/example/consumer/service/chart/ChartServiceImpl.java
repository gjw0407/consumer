package com.example.consumer.service.chart;

import com.example.consumer.dao.ChartDao;
import com.example.consumer.entity.Chart;
import com.example.consumer.entity.User;
import com.example.consumer.model.ChartDto;
import com.example.consumer.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService{
    private final ChartDao chartDao;

    @Override
    public ResponseEntity<Map<String, Object>> addChart(ChartDto chartDto) {
        Chart chartET = chartDto.toEntity();
        chartDao.save(chartET);
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
