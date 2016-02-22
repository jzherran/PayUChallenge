package com.spectre.mvc.dao;

import java.util.List;

import com.spectre.mvc.model.Route;

public interface RouteDao {

	List<Route> findAllRoutes();

	void saveRoute(Route route);

	Route findById(int id);

	void deleteRoute(Integer id);

}
