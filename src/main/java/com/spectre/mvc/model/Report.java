package com.spectre.mvc.model;

public class Report {

	private Route route;
	
	private Integer flights;
	
	private Integer passengers;

	public Report() {
		
	}
	
	public Report(Route route, Integer flights, Integer passengers) {
		super();
		this.route = route;
		this.flights = flights;
		this.passengers = passengers;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Integer getFlights() {
		return flights;
	}

	public void setFlights(Integer flights) {
		this.flights = flights;
	}

	public Integer getPassengers() {
		return passengers;
	}

	public void setPassengers(Integer passengers) {
		this.passengers = passengers;
	}
	
}
