package com.spectre.mvc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AIRPORT")
public class Airport {

	@Id
	@Column(name = "ID_AIRPORT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAirport;
	
	@Column(name = "NAME", nullable=false)
	private String name;
	
	@Column(name = "AIRPORT_CODE", nullable=false)
	private String airportCode;
	
	@OneToMany(targetEntity=Route.class, cascade={CascadeType.ALL},orphanRemoval=true)
	private List<Route> routes = new ArrayList<Route>();
	
	public Airport() {
		
	}
	
	public Airport(int idAirport, String name, String airportCode) {
		super();
		this.idAirport = idAirport;
		this.name = name;
		this.airportCode = airportCode;
	}
	
	public int getIdAirport() {
		return idAirport;
	}

	public void setIdAirport(int idAirport) {
		this.idAirport = idAirport;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Airport))
			return false;
		Airport other = (Airport) obj;
		if (idAirport != other.idAirport) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Airport [id=" + idAirport + ", name=" + name+ ", airportCode=" + airportCode+ "]";
	}

}
