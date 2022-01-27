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
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StatisticServiceTest {
    @Autowired
    private StatisticRepository repository;

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

    @Test
    void getAllStatisticTest(){
        Statistic todoSample = getSample();
        repository.save(todoSample);
        StatisticService service = new StatisticServiceImpl(repository);

        List<Statistic> toDoList = service.findAll();
        Statistic lastToDo = toDoList.get(toDoList.size()-1);
        assertTrue(toDoList.size()>0);

    }

    @Test
    void saveSuccesFullTest(){

        StatisticService service = new StatisticServiceImpl(repository);
        Statistic todoSample = getSample();

        service.save(todoSample);

        assertEquals(1.0, repository.count());
    }

    @Test
    void findByIdSuccessTest(){
        Statistic todoSample = getSample();
        repository.save(todoSample);
        StatisticService service = new StatisticServiceImpl(repository);
        Optional<Statistic> value=service.findById(2);

        assertEquals(value.isEmpty(),true);


    }

    public Statistic getSample(){
        Statistic result=new Statistic();
        result.setId(1);
        result.setCountry_name("Autagas");
        result.setState_name("Alabama");
        result.setDate(new Date());
        result.setCounty_fips(1000);
        result.setDate(new Date());
        result.setBaseline_jan_vmt(35713);
        result.setPercentageChangeFromJan(-0.6);
        result.setMeanCountryVmt(2724285.71);
        result.setMeanPercentChangeFromJan(-21.88);
        result.setDateAtLow(new Date());
        result.setMeanCountryVmtAtLow(1542857.14);
        result.setPercentChangeFromLow(80.83);
        return result;
    }




}
