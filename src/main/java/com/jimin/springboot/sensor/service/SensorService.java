package com.jimin.springboot.sensor.service;


import com.jimin.springboot.sensor.mapper.SensorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private final SensorMapper sensorMapper;

    @Autowired
    public SensorService(SensorMapper sensorMapper) {
        this.sensorMapper = sensorMapper;
    }



}
