package com.auto.service;

import com.auto.controller.StatisticController;
import com.auto.helper.CsvHelper;
import com.auto.model.Statistic;
import com.auto.repository.StatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {

    private static Logger LOGGER = LoggerFactory.getLogger(CSVService.class);
    @Autowired
    StatisticRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Statistic> tutorials = CsvHelper.csvToStatistic(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
