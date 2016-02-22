package com.spectre.mvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.spectre.mvc.model.Plane;
import com.spectre.mvc.service.PlaneService;

@Component
public class SelectedToPlaneConverter implements Converter<Object, Plane>{
 
    @Autowired
    PlaneService planeService;

    @Override
    public Plane convert(Object element) {
    	if(element instanceof Plane)
    		return (Plane) element;
    	else {
	        Integer id = Integer.parseInt((String)element);
	        Plane plane= planeService.findById(id);
	        return plane;
    	}
    }
     
}