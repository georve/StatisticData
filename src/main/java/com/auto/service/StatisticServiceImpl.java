package com.auto.service;

import com.auto.model.Statistic;
import com.auto.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticServiceImpl implements StatisticService{
    @Autowired
    private StatisticRepository repository;

    public StatisticServiceImpl(StatisticRepository repository){
        this.repository=repository;
    }


    @Override
    public List<Statistic> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Statistic save(Statistic any) {return this.repository.save(any);}

    @Override
    public Optional<Statistic> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {this.repository.deleteById(id);}
}
