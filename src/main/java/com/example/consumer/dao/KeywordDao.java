package com.example.consumer.dao;

import com.example.consumer.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface KeywordDao extends JpaRepository<Keyword, Integer> {
    Optional<Keyword> findByKeyword(String keyword);
    Keyword findById(int keywordId);

}

