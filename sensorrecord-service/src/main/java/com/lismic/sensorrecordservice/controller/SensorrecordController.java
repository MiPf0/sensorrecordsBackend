package com.lismic.sensorrecordservice.controller;

import com.lismic.sensorrecordservice.model.Sensorrecord;
import com.lismic.sensorrecordservice.repository.SensorrecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.*;

@RestController
@Slf4j
public class SensorrecordController {

    @Autowired
    private SensorrecordRepository repository;

    @PostMapping("/sensorrecords/addSensorrecord")
    @CrossOrigin(origins = "http://localhost:9191")
    public String saveSensorrecord(@RequestBody Sensorrecord sensorrecord) {
        repository.save(sensorrecord);
        log.info("Inside saveSensorrecord method of SensorrecordController");
        return "Added sensor record with id : " + sensorrecord.getId() + "; with Timestamp: " + sensorrecord.getTimestamp();
    }

    @GetMapping("/sensorrecords/findAllSensorrecords")
    @CrossOrigin(origins = "http://localhost:9191")
    public List<Sensorrecord> getSensorrecords() {
        log.info("Inside getSensorrecords method of SensorrecordController");
        return repository.findAll();
    }

    @GetMapping("/sensorrecords/findSensorrecord/{id}")
    @CrossOrigin(origins = "http://localhost:9191")
    public Optional<Sensorrecord> getSensorrecord(@PathVariable String id) {
        log.info("Inside getSensorrecord method of SensorrecordController");
        return repository.findById(id);
    }

    @GetMapping("/sensorrecords/findSensorrecords/sensors/{sensorId}")
    @CrossOrigin(origins = "http://localhost:9191")
    public List<Sensorrecord> getSensorrecords(@PathVariable String sensorId) {
        log.info("Inside getSensorrecord method of SensorrecordController");
        return repository.findSensorrecordsBySensorId(sensorId);
    }

    /*@GetMapping("/sensorrecords/findNewestEntryPerSensorId")
    @CrossOrigin(origins = "http://localhost:9191")
    public List<Sensorrecord> findNewestEntryPerSensorId() {

        List<Sensorrecord> allSensorrecords = repository.findAll();

        Collections.sort(allSensorrecords, Comparator.comparing(Sensorrecord::getTimestamp));
        Collections.sort(allSensorrecords, Comparator.comparing(Sensorrecord::getSensorId));

        return allSensorrecords;
    }*/

    @PutMapping("/sensorrecords/updateSensorrecord/{id}")
    @CrossOrigin(origins = "http://localhost:9191")
    public ResponseEntity<Sensorrecord> updateSensorrecord(@PathVariable("id") String id, @RequestBody Sensorrecord sensorrecord) {
        Optional<Sensorrecord> optionalSensorrecord = repository.findById(id);

        if (optionalSensorrecord.isPresent()) {
            Sensorrecord _sensorrecord = optionalSensorrecord.get();
            _sensorrecord.setDate(sensorrecord.getDate());
            _sensorrecord.setTimestamp(sensorrecord.getTimestamp());
            _sensorrecord.setSensorId(sensorrecord.getSensorId());
            _sensorrecord.setTemperature(sensorrecord.getTemperature());
            _sensorrecord.setHumidity(sensorrecord.getHumidity());
            _sensorrecord.setShowData(sensorrecord.isShowData());
            return new ResponseEntity<>(repository.save(_sensorrecord), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/sensorrecords/deleteSensorrecord/{id}")
    @CrossOrigin(origins = "http://localhost:9191")
    public String deleteSensorrecord(@PathVariable String id) {
        repository.deleteById(id);
        log.info("Inside deleteSensorrecord method of SensorrecordController");
        return "Sensorrecord deleted with id: " + id;
    }

}