package com.spectre.mvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.spectre.mvc.model.Flight;
import com.spectre.mvc.service.FlightService;

@Component
public class SelectedToFlightConverter implements Converter<Object, Flight>{
 
    @Autowired
    FlightService flightService;

    @Override
    public Flight convert(Object element) {
    	if(element instanceof Flight)
    		return (Flight) element;
    	else {
	        Integer id = Integer.parseInt((String)element);
	        Flight flight= flightService.findById(id);
	        return flight;
    	}
    }
     
}