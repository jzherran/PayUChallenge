package com.spectre.mvc.dao;

import java.util.List;

import com.spectre.mvc.model.Airport;

public interface AirportDao {

	List<Airport> findAllAirports();

	Airport findById(Integer id);

}
