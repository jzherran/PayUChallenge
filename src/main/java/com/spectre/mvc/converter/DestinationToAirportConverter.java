package com.spectre.mvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.spectre.mvc.model.Airport;
import com.spectre.mvc.service.AirportService;

@Component
public class DestinationToAirportConverter implements Converter<Object, Airport>{
 
    @Autowired
    AirportService airportService;

    @Override
    public Airport convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        Airport airport= airportService.findById(id);
        return airport;
    }
     
}