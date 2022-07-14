package com.example.consumer.controller;

import com.example.consumer.model.ChartDto;
import com.example.consumer.model.KeywordDto;
import com.example.consumer.model.UserDto;
import com.example.consumer.service.KeywordService;
import com.example.consumer.service.chart.ChartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Controller
public class ChartController {
    private final ChartService chartService;
    private final KeywordService keywordService;

//    @GetMapping("/chart/")
//    public ResponseEntity<Map<String, Object>> addChart() {
//        return metadata chart
//    }
//}

    @PostMapping("/chart/")
    public ResponseEntity<Map<String, Object>> addChart(@RequestParam String keyword,
                                                           @RequestParam String startDate,
                                                           @RequestParam String endDate,
                                                           @RequestParam int period,
                                                           @RequestParam String chartType) {

        // TODO Get user ID FROM SEESION
        int userId = 1; // TODO GET THIS FROM DB USER ID

        KeywordDto keywordDto = new KeywordDto();
        keywordDto.setKeyword(keyword);

        int keywordId = keywordService.addKeyword(keywordDto);

        log.info("Regiseter - 호출");

        ChartDto chartDto = new ChartDto();
        chartDto.setKeywordId(keywordId);
        chartDto.setUserId(userId);
        chartDto.setPeriodSec(period);
        chartDto.setStartDate(startDate);
        chartDto.setEndDate(endDate);

        System.out.println(keyword + startDate + endDate + period + chartType);

        return chartService.addChart(chartDto);
    }
}
