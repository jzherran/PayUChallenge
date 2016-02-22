package com.spectre.mvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.spectre.mvc.model.Passenger;
import com.spectre.mvc.service.PassengerService;

@Component
public class SelectedToPassengerConverter implements Converter<Object, Passenger>{
 
    @Autowired
    PassengerService passengerService;

    @Override
    public Passenger convert(Object element) {
    	if(element instanceof Passenger)
    		return (Passenger) element;
    	else {
	        Integer id = Integer.parseInt((String)element);
	        Passenger passenger = passengerService.findById(id);
	        return passenger;
    	}
    }
     
}