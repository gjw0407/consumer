package com.example.consumer.service.chart;

import com.example.consumer.dao.ChartDao;
import com.example.consumer.dao.UserDao;
import com.example.consumer.entity.Chart;
import com.example.consumer.entity.User;
import com.example.consumer.model.ChartDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChartServiceImpl implements ChartService{
    private final ChartDao chartDao;
    private final UserDao userDao;

    @Override
    public ResponseEntity<Map<String, Object>> addChart(ChartDto chartDto) {
        Chart chartET = chartDto.toEntity();
        chartDao.save(chartET);
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
    }

    @Override
    public List<ChartDto> loadChart(String email) {
        User user = userDao.findByEmail(email);
        int userId = user.getUserId();
        List<Chart> chartList = chartDao.findAllByUserId(userId);
        List<ChartDto> chartDtoList = new ArrayList<>();
        chartList.stream().forEach(
                chart -> {
                    ChartDto chartDto = new ChartDto();
                    chartDto.setUserId(chart.getUserId());
                    chartDto.setKeywordId(chart.getKeywordId());
                    chartDto.setEndDate(chart.getEndDate());
                    chartDto.setPeriodSec(chart.getPeriodSec());
                    chartDto.setStartDate(chart.getStartDate());
                    chartDtoList.add(chartDto);
                });
        return chartDtoList;
    }

}
