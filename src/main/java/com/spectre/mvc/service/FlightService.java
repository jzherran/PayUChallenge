package com.spectre.mvc.service;

import java.util.Calendar;
import java.util.List;

import com.spectre.mvc.model.Flight;
import com.spectre.mvc.model.Purchase;

public interface FlightService {

	List<Flight> findAllFlights();
	
	List<Flight> findAllFlightsAvailable();

	void saveFlight(Flight flight);

	Flight findById(int id);

	void deleteFlight(Integer id);
	
	boolean validateAvailabilityPlane(Flight flight, Calendar init, Calendar end);

	void updateFlight(Flight flight);
	
	boolean isActiveFlight(Integer id);

	void savePurchase(Purchase purchase);
	
}
