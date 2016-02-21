package com.spectre.mvc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.spectre.mvc.dao.AbstractDao;
import com.spectre.mvc.dao.RouteDao;
import com.spectre.mvc.model.Route;

@Repository("routeDao")
public class RouteDaoImpl extends AbstractDao<Integer, Route> implements RouteDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Route> findAllRoutes() {
		Criteria criteria = createEntityCriteria();
		return (List<Route>)criteria.list();
	}

	@Override
	public void saveRoute(Route route) {
		persist(route);
	}

	@Override
	public Route findById(int id) {
		return getByKey(id);
	}
	
}
