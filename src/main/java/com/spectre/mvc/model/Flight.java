package com.spectre.mvc.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "FLIGHT")
public class Flight {

	@Transient
	private static SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");

	@Transient
	private static SimpleDateFormat sdt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");

	@Id
	@Column(name = "ID_FLIGHT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFlight;

	@ManyToOne(targetEntity = Route.class)
	@JoinColumn(name = "ROUTE")
	private Route route;

	@ManyToOne(targetEntity = Plane.class)
	@JoinColumn(name = "PLANE")
	private Plane plane;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Calendar date;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIME_INIT")
	private Calendar timeInit;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIME_END")
	private Calendar timeEnd;

	@Transient
	private String timeInitVisual;

	@Transient
	private String timeEndVisual;

	public Flight() {

	}

	public Flight(Integer idFlight, Route route, Plane plane, Calendar date, Calendar timeInit, Calendar timeEnd) {
		super();
		this.idFlight = idFlight;
		this.route = route;
		this.plane = plane;
		this.date = date;
		this.timeInit = timeInit;
		this.timeEnd = timeEnd;
	}

	public Integer getIdFlight() {
		return idFlight;
	}

	public void setIdFlight(Integer idFlight) {
		this.idFlight = idFlight;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public Calendar getDateCal() {
		return date;
	}

	public String getDate() {
		if (date != null)
			return sd.format(date.getTime());
		else
			return "";
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Calendar getTimeInitCal() {
		return timeInit;
	}

	public String getTimeInit() {
		if (timeInit != null)
			return sdt.format(timeInit.getTime());
		else
			return "";
	}

	public void setTimeInit(Calendar timeInit) {
		this.timeInit = timeInit;
	}

	public Calendar getTimeEndCal() {
		return timeEnd;
	}

	public String getTimeEnd() {
		if (timeEnd != null)
			return sdt.format(timeEnd.getTime());
		else
			return "";
	}

	public void setTimeEnd(Calendar timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getTimeInitVisual() {
		return sdt.format(timeInit.getTime());
	}

	public String getTimeEndVisual() {
		return sdt.format(timeEnd.getTime());
	}

	public String getFlightCode() {
		if (idFlight != null)
			return "SPEC-" + idFlight + "" + route.getAirportOrigin().getIdAirport() + ""
					+ route.getAirportDestination().getIdAirport();
		else
			return "";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Flight))
			return false;
		Flight other = (Flight) obj;
		if (idFlight != other.idFlight) {
			return false;
		}
		return true;
	}
}
