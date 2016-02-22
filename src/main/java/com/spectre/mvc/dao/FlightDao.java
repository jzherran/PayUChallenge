package com.spectre.mvc.dao;

import java.util.Calendar;
import java.util.List;

import com.spectre.mvc.model.Flight;
import com.spectre.mvc.model.Purchase;

public interface FlightDao {

	List<Flight> findAllFlights();

	void saveFlight(Flight flight);

	Flight findById(int id);

	boolean validateAvailabilityPlane(Flight flight, Calendar init, Calendar end);

	List<Flight> findAllFlightsAvailable();

	boolean isActiveFlight(Integer id);

	void savePurchase(Purchase purchase);

}
