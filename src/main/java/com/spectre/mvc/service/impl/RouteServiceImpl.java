package com.spectre.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spectre.mvc.dao.RouteDao;
import com.spectre.mvc.model.Route;
import com.spectre.mvc.service.RouteService;

@Service("routeService")
@Transactional
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteDao dao;
	
	
	@Override
	public List<Route> findAllRoutes() {
		return dao.findAllRoutes();
	}

	@Override
	public void saveRoute(Route route) {
		dao.saveRoute(route);
	}

	@Override
	public Route findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void updateRoute(Route route) {
		Route entity = dao.findById(route.getIdRoute());
		if(entity != null) {
			entity.setAirportOrigin(route.getAirportOrigin());
			entity.setAirportDestination(route.getAirportDestination());
			entity.setTime(route.getTime());
			entity.setTimeToVisual();
		}
	}

	@Override
	public void deleteRouteById(Integer id) {
		dao.deleteRoute(id);
	}

}
