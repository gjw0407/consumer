package com.example.consumer.dao;

import com.example.consumer.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KeywordDao extends JpaRepository<Keyword, Integer> {
}

