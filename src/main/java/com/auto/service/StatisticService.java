package com.auto.service;

import com.auto.model.Statistic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StatisticService {
    List<Statistic> findAll();

    Statistic save(Statistic any);

    Optional<Statistic> findById(Integer id);

    void deleteById(Integer id);
}
