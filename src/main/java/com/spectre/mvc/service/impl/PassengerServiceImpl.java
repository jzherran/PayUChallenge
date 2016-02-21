package com.spectre.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spectre.mvc.dao.PassengerDao;
import com.spectre.mvc.model.Passenger;
import com.spectre.mvc.service.PassengerService;

@Service("passengerService")
@Transactional
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerDao dao;
	
	public Passenger findById(int id) {
		return dao.findById(id);
	}

	public void savePassenger(Passenger passenger) {
		dao.savePassenger(passenger);
	}

	public void updatePassenger(Passenger passenger) {
		Passenger entity = dao.findById(passenger.getIdPassenger());
		if(entity!=null){
			entity.setFirstName(passenger.getFirstName());
			entity.setLastName(passenger.getLastName());
			entity.setIdentificationNumber(passenger.getIdentificationNumber());
			entity.setEmail(passenger.getEmail());
			entity.setAge(passenger.getAge());
		}
	}

	@Override
	public void deletePassengerById(Integer id) {
		dao.deletePassenger(id);
	}
	
	@Override
	public List<Passenger> findAllPassengers() {
		return dao.findAllPassengers();
	}

	@Override
	public Passenger findPassengerByIN(String in) {
		return dao.findPassengerByIN(in);
	}
	
	@Override
	public Passenger findPassengerByEmail(String email) {
		return dao.findPassengerByEmail(email);
	}

	@Override
	public boolean isPassengerINUnique(Integer id, String idn) {
		Passenger passenger = findPassengerByIN(idn);
		return ( passenger == null || ((id != null) && (passenger.getIdPassenger() == id)));
	}
	
	@Override
	public boolean isPassengerEmailUnique(Integer id, String email) {
		Passenger passenger = findPassengerByEmail(email);
		return ( passenger == null || ((id != null) && (passenger.getIdPassenger() == id)));
	}
}
