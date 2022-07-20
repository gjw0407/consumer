package com.example.consumer.controller;

import com.example.consumer.entity.Chart;
import com.example.consumer.model.ChartDto;
import com.example.consumer.model.KeywordDto;
import com.example.consumer.model.UserDto;
import com.example.consumer.service.KeywordService;
import com.example.consumer.service.chart.ChartService;
import com.example.consumer.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChartController {
    private final ChartService chartService;
    private final KeywordService keywordService;
    private final UserService userService;

    @GetMapping("/chart/load")
    public List<ChartDto> loadChart(HttpServletRequest request) {
        String email = request.getHeader("User-Email");
        log.info("loading chart controller");

        return chartService.loadChart(email);

//        System.out.println(chartList.toString());

    }

    @PostMapping("/chart/")
    public ResponseEntity<Map<String, Object>> addChart(@RequestParam String keyword,
                                @RequestParam String startDate,
                                @RequestParam String endDate,
                                @RequestParam int period,
                                @RequestParam String chartType,
                                HttpServletRequest request) {

        String email = request.getHeader("User-Email");
        int userId = userService.getUserId(email);

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
