package com.example.consumer.service;

import com.example.consumer.dao.KeywordDao;
import com.example.consumer.dao.UserDao;
import com.example.consumer.entity.Keyword;
import com.example.consumer.model.KeywordDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class KeywordService {
    private final KeywordDao keywordDao;

    public void addKeyword(KeywordDto keywordDto) {
        Keyword keyword = keywordDto.toEntity();
        keywordDao.save(keyword);
    }
}
