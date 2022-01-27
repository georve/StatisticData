package com.auto.controller;

import com.auto.exception.IllegalArgumentClientException;
import com.auto.exception.ResourceNotFoundException;
import com.auto.model.Statistic;
import com.auto.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticController {

    @Autowired
    private StatisticService service;

    @GetMapping()
    public ResponseEntity<List<Statistic>> getAllStatistic() throws Exception{
        List<Statistic> list=service.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping()
    public ResponseEntity<Statistic> saveStatistic(@RequestBody Statistic sta)throws Exception{
        Statistic saved=service.save(sta);
        return new ResponseEntity(saved,HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Statistic> getAllStatistic(@PathVariable(value="id") Integer id) throws Exception{
        Optional<Statistic> value=service.findById(id);
        if (value.isEmpty()) {
            throw new ResourceNotFoundException("Statistic with ID " + id + " does not exist.");
        }

        return ResponseEntity.ok(value.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Statistic> update(@RequestBody Statistic record,@PathVariable Integer id) throws ResourceNotFoundException, IllegalArgumentClientException {
        if (record == null || record.getId() == null) {
            throw new IllegalArgumentClientException("Statistic or ID must not be null!");
        }
        Optional<Statistic> optionalRecord = service.findById(id);
        if (optionalRecord.isEmpty()) {
            throw new ResourceNotFoundException("Statistic with ID " + record.getId() + " does not exist.");
        }
        Statistic existingPatientRecord = optionalRecord.get();

        existingPatientRecord.setState_name(record.getState_name());
        existingPatientRecord.setCountry_name(record.getCountry_name());
        existingPatientRecord.setCounty_fips(record.getCounty_fips());

        return ResponseEntity.ok(service.save(existingPatientRecord));
    }


    @DeleteMapping(value = "/{id}")
    public void deletePatientById(@PathVariable(value = "id") Integer id) throws Exception {
        if (service.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Statistic with ID " + id + " does not exist.");
        }
        service.deleteById(id);
    }
}
