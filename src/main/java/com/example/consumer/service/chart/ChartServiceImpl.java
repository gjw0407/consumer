package com.example.consumer.service.chart;

import com.example.consumer.dao.ChartDao;
import com.example.consumer.dao.KeywordDao;
import com.example.consumer.dao.UserDao;
import com.example.consumer.entity.Chart;
import com.example.consumer.entity.User;
import com.example.consumer.model.ChartDto;
import com.example.consumer.model.ChartDtoKeyword;
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
    private final KeywordDao keywordDao;

    @Override
    public ResponseEntity<Map<String, Object>> addChart(ChartDto chartDto) {
        Chart chartET = chartDto.toEntity();
        chartDao.save(chartET);
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
    }

    @Override
    public List<ChartDtoKeyword> loadChart(int emailId) {

        List<Chart> chartList = chartDao.findAllByUserId(emailId);
        List<ChartDtoKeyword> chartDtoKeywordList = new ArrayList<>();

        // ChartDtoKeyword
        chartList.forEach(
                chart -> {
                    String keyword = keywordDao.findById(chart.getKeywordId()).getKeyword();
                    System.out.println("Keyword: " + keyword);
                    ChartDtoKeyword chartDtoKeyword = new ChartDtoKeyword();
                    chartDtoKeyword.setUserId(chart.getUserId());
                    chartDtoKeyword.setKeyword(keyword);
                    chartDtoKeyword.setEndDate(chart.getEndDate());
                    chartDtoKeyword.setPeriodSec(chart.getPeriodSec());
                    chartDtoKeyword.setStartDate(chart.getStartDate());
                    chartDtoKeywordList.add(chartDtoKeyword);
                });
        return chartDtoKeywordList;
    }

}
