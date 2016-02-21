package com.spectre.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ROUTE")
public class Route {

	@Id
	@Column(name = "ID_ROUTE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRoute;

	@Column(name = "TIME", nullable = false)
	private long time;

	@Transient
	private String timeToVisual;

	@ManyToOne(targetEntity = Airport.class)
	@JoinColumn(name = "AIRPORT_ORIGIN")
	private Airport airportOrigin;

	@ManyToOne(targetEntity = Airport.class)
	@JoinColumn(name = "AIRPORT_DESTINATION")
	private Airport airportDestination;

	public Route() {

	}

	public Route(int idRoute, long time, Airport airportOrigin, Airport airportDestination) {
		super();
		this.idRoute = idRoute;
		this.time = time;
		this.airportOrigin = airportOrigin;
		this.airportDestination = airportDestination;
	}

	public int getIdRoute() {
		return idRoute;
	}

	public void setIdRoute(int idRoute) {
		this.idRoute = idRoute;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Airport getAirportOrigin() {
		return airportOrigin;
	}

	public void setAirportOrigin(Airport airportOrigin) {
		this.airportOrigin = airportOrigin;
	}

	public Airport getAirportDestination() {
		return airportDestination;
	}

	public void setAirportDestination(Airport airportDestination) {
		this.airportDestination = airportDestination;
	}

	public String getTimeToVisual() {
		return splitToComponentTimes(time);
	}

	public void setTimeToVisual(String timeToVisual) {
		this.timeToVisual = timeToVisual;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Route))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Route [id=" + idRoute + ", time=" + time + ", origin=" + airportOrigin + ", destiny="
				+ airportDestination + "]";
	}

	public String splitToComponentTimes(long longVal) {
		int hours = (int) longVal / 3600;
		int remainder = (int) longVal - hours * 3600;
		int mins = remainder / 60;
		remainder = remainder - mins * 60;
		int secs = remainder;

		return hours + " H : " + mins + " M : " + secs + " S";
	}
}
