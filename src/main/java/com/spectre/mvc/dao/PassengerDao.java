package com.spectre.mvc.dao;

import java.util.List;

import com.spectre.mvc.model.Passenger;

public interface PassengerDao {

	Passenger findById(int id);

	void savePassenger(Passenger passenger);
	
	void deletePassenger(Integer id);
	
	List<Passenger> findAllPassengers();

	Passenger findPassengerByIN(String in);

	Passenger findPassengerByEmail(String email);

}
