package com.spectre.mvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.spectre.mvc.model.Route;
import com.spectre.mvc.service.RouteService;

@Component
public class SelectedToRouteConverter implements Converter<Object, Route>{
 
    @Autowired
    RouteService routeService;

    @Override
    public Route convert(Object element) {
    	if(element instanceof Route)
    		return (Route) element;
    	else {
	        Integer id = Integer.parseInt((String)element);
	        Route route = routeService.findById(id);
	        return route;
    	}
    }
     
}