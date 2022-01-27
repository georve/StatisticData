package com.auto.service;

import com.auto.helper.CsvHelper;
import com.auto.model.Statistic;
import com.auto.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {

    @Autowired
    StatisticRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Statistic> tutorials = CsvHelper.csvToStatistic(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
