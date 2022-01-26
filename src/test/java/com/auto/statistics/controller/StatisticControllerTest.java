package com.auto.statistics.controller;

import com.auto.model.Statistic;
import com.auto.service.StatisticService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticService statisticService;

    @Test
    public void testGetAllStatistic_success() throws Exception{

        List<Statistic> statisticList = new ArrayList<Statistic>();
        statisticList.addAll(this.getStatisticMock());

        when(statisticService.findAll()).thenReturn(statisticList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/statistic")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andDo(print());

    }

    @Test
    void successfullyCreateStatistic() throws Exception {
        Statistic eatToDo = getSingleStatistic();
        when(statisticService.save(eatToDo)).thenReturn(eatToDo);
        ObjectMapper objectMapper = new ObjectMapper();
        String eatToDoJSON = objectMapper.writeValueAsString(eatToDo);

        mockMvc.perform(post("/api/v1/statistic")
                .contentType(MediaType.APPLICATION_JSON)
                .content(eatToDoJSON)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.country_name").value("Autauga"))
                .andExpect(jsonPath("$.state_name").value("Alabama"));
    }

    @Test
    void successfulGetByIdTest() throws Exception{
        Statistic eatToDo = getSingleStatistic();
        when(statisticService.findById(1)).thenReturn(Optional.of(eatToDo));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/statistic/1")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.country_name").value("Autauga"))
                .andExpect(jsonPath("$.state_name").value("Alabama"));
    }

    @Test
    void resourceNotFoundByIdTest() throws Exception{

        Statistic eatToDo = getSingleStatistic();
        when(statisticService.findById(1)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/statistic/2")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"));


    }

    void updateSucessFullTest() throws Exception {

    }

    private List<Statistic> getStatisticMock() {
        List<Statistic> statisticList = new ArrayList<Statistic>();
        statisticList.add(this.getSingleStatistic());
        return statisticList;
    }
    private Statistic getSingleStatistic(){
        Statistic data=new Statistic();
        data.setId(1);
        data.setCountry_name("Autauga");
        data.setState_name("Alabama");
        return data;
    }
}
