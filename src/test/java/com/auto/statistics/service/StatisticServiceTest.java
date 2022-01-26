package com.auto.statistics.service;

import com.auto.model.Statistic;
import com.auto.repository.StatisticRepository;
import com.auto.service.StatisticService;
import com.auto.service.StatisticServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StatisticServiceTest {
    @Autowired
    private StatisticRepository repository;

    @AfterEach
    void tearDown(){
        System.out.println("borro");
        repository.deleteAll();
    }

    @Test
    void getAllStatisticTest(){
        System.out.println("primera");
        Statistic todoSample = new Statistic(1,1000,"Uhat","Arizona",new Date(),1);
        repository.save(todoSample);
        StatisticService service = new StatisticServiceImpl(repository);

        List<Statistic> toDoList = service.findAll();
        Statistic lastToDo = toDoList.get(toDoList.size()-1);

        assertEquals(todoSample.getId(), lastToDo.getId());
    }

    @Test
    void saveSuccesFullTest(){
        System.out.println("segunda");
        StatisticService service = new StatisticServiceImpl(repository);
        Statistic todoSample = new Statistic(2,1000,"Uhat","Arizona",new Date(),1);

        service.save(todoSample);

        assertEquals(1.0, repository.count());
    }

    @Test
    void findByIdSuccessTest(){
        System.out.println("tercera");
        Statistic todoSample = new Statistic(3,1000,"Uhat","Arizona",new Date(),1);
        repository.save(todoSample);
        StatisticService service = new StatisticServiceImpl(repository);
        Optional<Statistic> value=service.findById(2);

        assertEquals(value.isEmpty(),false);
        assertEquals(value.get().getId(),2);


    }




}
