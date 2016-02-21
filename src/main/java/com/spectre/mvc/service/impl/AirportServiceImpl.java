package com.spectre.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spectre.mvc.dao.AirportDao;
import com.spectre.mvc.model.Airport;
import com.spectre.mvc.service.AirportService;

@Service("airportService")
@Transactional
public class AirportServiceImpl implements AirportService {

	@Autowired
	private AirportDao dao;
	
	@Override
	public List<Airport> findAllAirports() {
		return dao.findAllAirports();
	}

	@Override
	public Airport findById(Integer id) {
		return dao.findById(id);
	}

}
