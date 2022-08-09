package com.example.consumer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.consumer.model.ChartDtoKeyword;
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
    public List<ChartDtoKeyword> loadChart(HttpServletRequest request) {
        log.info("Controller - /chart/load");
        String email = jwtService.getEmail(request.getHeader("Access-Token"));
        int userId = userService.getUserId(email);

        return chartService.loadChart(userId);
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

        int keywordId = keywordService.addKeyword(
                KeywordDto.builder()
                .keyword(keyword)
                .build());

        log.info("Regiseter - 호출");

        System.out.println(keyword + startDate + endDate + period + chartType);

        return chartService.addChart(
                ChartDto.builder()
                .keywordId(keywordId)
                .userId(userId)
                .periodSec(period)
                .startDate(startDate)
                .endDate(endDate)
                .build());
    }
}
