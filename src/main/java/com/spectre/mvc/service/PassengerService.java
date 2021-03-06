package com.spectre.mvc.service;

import java.util.List;

import com.spectre.mvc.model.Passenger;

public interface PassengerService {

	Passenger findById(int id);
	
	void savePassenger(Passenger passenger);
	
	void updatePassenger(Passenger passenger);
	
	List<Passenger> findAllPassengers(); 
	
	Passenger findPassengerByIN(String in);

	boolean isPassengerINUnique(Integer id, String idn);

	void deletePassengerById(Integer id);

	boolean isPassengerEmailUnique(Integer id, String email);

	Passenger findPassengerByEmail(String email);
	
}
