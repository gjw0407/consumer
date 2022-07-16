package com.example.consumer.dao;

import com.example.consumer.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChartDao extends JpaRepository<Chart, Integer> {
    List<Chart> findAllByUserId(int userId);
}
