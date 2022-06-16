package com.example.consumer.dao;

import com.example.consumer.entity.ChartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartDao extends JpaRepository<ChartData, Integer> {
}
