package com.spectre.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spectre.mvc.dao.PassengerDao;
import com.spectre.mvc.model.Passenger;

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

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updatePassenger(Passenger passenger) {
		Passenger entity = dao.findById(passenger.getIdPassenger());
		if(entity!=null){
			entity.setFirstName(passenger.getFirstName());
			entity.setLastName(passenger.getLastName());
			entity.setIdentificationNumber(passenger.getIdentificationNumber());
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
	public boolean isPassengerINUnique(Integer id, String in) {
		Passenger passenger = findPassengerByIN(in);
		return ( passenger == null || ((id != null) && (passenger.getIdPassenger() == id)));
	}
}
