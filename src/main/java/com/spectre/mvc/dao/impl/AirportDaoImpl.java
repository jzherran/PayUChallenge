package com.spectre.mvc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.spectre.mvc.dao.AbstractDao;
import com.spectre.mvc.dao.AirportDao;
import com.spectre.mvc.model.Airport;

@Repository("airportDao")
public class AirportDaoImpl extends AbstractDao<Integer, Airport> implements AirportDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Airport> findAllAirports() {
		Criteria criteria = createEntityCriteria();
		return (List<Airport>)criteria.list();
	}

	@Override
	public Airport findById(Integer id) {
		return getByKey(id);
	}
}
