package com.spectre.mvc.service;

import java.util.List;

import com.spectre.mvc.model.Route;

public interface RouteService {

	List<Route> findAllRoutes();
	
	void saveRoute(Route route);

	Route findById(int id);

	void updateRoute(Route route);

	void deleteRouteById(Integer id);
}
