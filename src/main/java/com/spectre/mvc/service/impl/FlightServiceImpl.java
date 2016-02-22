package com.spectre.mvc.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spectre.mvc.dao.FlightDao;
import com.spectre.mvc.model.Flight;
import com.spectre.mvc.model.Purchase;
import com.spectre.mvc.service.FlightService;

@Service("flightService")
@Transactional
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightDao dao;

	@Override
	public List<Flight> findAllFlights() {
		return dao.findAllFlights();
	}
	
	@Override
	public List<Flight> findAllFlightsAvailable() {
		return dao.findAllFlightsAvailable();
	}

	@Override
	public void saveFlight(Flight flight) {
		dao.saveFlight(flight);
	}

	@Override
	public Flight findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void deleteFlight(Integer id) {
		dao.deleteFlight(id);
	}

	@Override
	public boolean validateAvailabilityPlane(Flight flight, Calendar init, Calendar end) {
		return dao.validateAvailabilityPlane(flight, init, end);
	}

	@Override
	public void updateFlight(Flight flight) {
		Flight entity = dao.findById(flight.getIdFlight());
		if (entity != null) {
			entity.setPlane(flight.getPlane());
			entity.setRoute(flight.getRoute());
			entity.setTimeInit(flight.getTimeInitCal());
			entity.setTimeEnd(flight.getTimeEndCal());
		}
	}

	@Override
	public boolean isActiveFlight(Integer id) {
		return dao.isActiveFlight(id);
	}

	@Override
	public void savePurchase(Purchase purchase) {
		dao.savePurchase(purchase);
	}

}
