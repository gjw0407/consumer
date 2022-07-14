package com.example.consumer.service;

import com.example.consumer.dao.KeywordDao;
import com.example.consumer.dao.UserDao;
import com.example.consumer.entity.Keyword;
import com.example.consumer.model.KeywordDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class KeywordService {
    private final KeywordDao keywordDao;

    public int addKeyword(KeywordDto keywordDto) {
        int keywordId = checkKeyword(keywordDto);

        // Keyword 있음
        if (keywordId != -1) { //keyword.get().getKeywordId();
            return keywordId;
        }
        // Keyword 없음
        Keyword keyword = keywordDto.toEntity();
        keywordDao.save(keyword); // Optional<Keyword> keyword = keywordDao.findByKeyword(keywordDto.getKeyword());
        return checkKeyword(keywordDto); //keyword.get().getKeywordId();

    }

    public int checkKeyword(KeywordDto keywordDto) {
        Optional<Keyword> keyword = keywordDao.findByKeyword(keywordDto.getKeyword());
        if (keyword.isPresent()) {
            return keyword.get().getKeywordId();
        }
        return -1;
    }
}