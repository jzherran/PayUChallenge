package com.spectre.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PURCHASE")
public class Purchase {

	@Id
	@Column(name = "ID_PURCHASE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPurchase;

	@NotNull
	@ManyToOne(targetEntity = Passenger.class)
	@JoinColumn(name = "PASSENGER")
	private Passenger passenger;
	
	@NotNull
	@ManyToOne(targetEntity = Flight.class)
	@JoinColumn(name = "FLIGHT")
	private Flight flight;

	public int getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(int idPurchase) {
		this.idPurchase = idPurchase;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	public Purchase() {

	}

	public Purchase(int idPurchase, Passenger passenger, Flight flight) {
		super();
		this.idPurchase = idPurchase;
		this.passenger = passenger;
		this.flight = flight;
	}
	
}
