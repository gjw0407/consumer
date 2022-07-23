package com.example.consumer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumer.model.ChartDto;
import com.example.consumer.model.KeywordDto;
import com.example.consumer.service.KeywordService;
import com.example.consumer.service.chart.ChartService;
import com.example.consumer.service.jwt.JwtService;
import com.example.consumer.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChartController {
    private final ChartService chartService;
    private final KeywordService keywordService;
    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/chart/load")
    public List<ChartDto> loadChart(HttpServletRequest request) {
    	String email = jwtService.getEmail(request.getHeader("Access-Token"));
        log.info("loading chart controller");

        return chartService.loadChart(email); // 아래와 동일하게 userId 전달
//        System.out.println(chartList.toString());
    }

    @PostMapping("/chart/")
    public ResponseEntity<Map<String, Object>> addChart(@RequestParam String keyword,
                                @RequestParam String startDate,
                                @RequestParam String endDate,
                                @RequestParam int period,
                                @RequestParam String chartType,
                                HttpServletRequest request) {

        String email = jwtService.getEmail(request.getHeader("Access-Token"));
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
