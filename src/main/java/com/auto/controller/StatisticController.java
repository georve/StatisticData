package com.auto.controller;

import com.auto.exception.IllegalArgumentClientException;
import com.auto.exception.ResourceNotFoundException;
import com.auto.helper.AttrCopy;
import com.auto.helper.CsvHelper;
import com.auto.model.Statistic;
import com.auto.response.ResponseMessage;
import com.auto.service.CSVService;
import com.auto.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticController {

    private static Logger LOGGER = LoggerFactory.getLogger(StatisticController.class);

    @Autowired
    private StatisticService service;

    @Autowired
    private CSVService csvService;

    @GetMapping()
    public ResponseEntity<List<Statistic>> getAllStatistic() throws Exception{
        LOGGER.debug("Get all statistic");
        List<Statistic> list=service.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping()
    public ResponseEntity<Statistic> saveStatistic(@RequestBody Statistic sta)throws Exception{
        LOGGER.debug("save statistic with country"+sta.getCountry_name());
        Statistic saved=service.save(sta);
        LOGGER.debug("statistic saved successfully with country"+sta.getCountry_name());
        return new ResponseEntity(saved,HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Statistic> getAllStatistic(@PathVariable(value="id") Integer id) throws Exception{
        LOGGER.debug("Get statistic with id:"+id);
        Optional<Statistic> value=service.findById(id);
        if (value.isEmpty()) {
            throw new ResourceNotFoundException("Statistic with ID " + id + " does not exist.");
        }

        return ResponseEntity.ok(value.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Statistic> update(@RequestBody Statistic record,@PathVariable Integer id) throws ResourceNotFoundException, IllegalArgumentClientException, IllegalAccessException {
        if (record == null || record.getId() == null) {
            throw new IllegalArgumentClientException("Statistic or ID must not be null!");
        }
        Optional<Statistic> optionalRecord = service.findById(id);
        if (!optionalRecord.isPresent()) {
            throw new ResourceNotFoundException("Statistic with ID " + record.getId() + " does not exist.");
        }
        Statistic existingPatientRecord = optionalRecord.get();
        AttrCopy.copyAttributes(record,existingPatientRecord);
        return ResponseEntity.ok(service.save(existingPatientRecord));
    }


    @DeleteMapping(value = "/{id}")
    public void deletePatientById(@PathVariable(value = "id") Integer id) throws Exception {
        Optional<Statistic> optionalRecord=service.findById(id);
        if (!optionalRecord.isPresent()) {
            throw new ResourceNotFoundException("Statistic with ID " + id + " does not exist.");
        }
        service.deleteById(id);
    }


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CsvHelper.hasCSVFormat(file)) {
            try {
                csvService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }



}
