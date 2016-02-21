package com.spectre.mvc.service;

import java.util.List;

import com.spectre.mvc.model.Airport;

public interface AirportService {

	List<Airport> findAllAirports();

	Airport findById(Integer id);

}
