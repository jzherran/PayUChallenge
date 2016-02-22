package com.spectre.mvc.dao.impl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.spectre.mvc.dao.AbstractDao;
import com.spectre.mvc.dao.FlightDao;
import com.spectre.mvc.model.Flight;
import com.spectre.mvc.model.Purchase;

@Repository("flightDao")
public class FlightDaoImpl extends AbstractDao<Integer, Flight> implements FlightDao {

	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@SuppressWarnings("unchecked")
	@Override
	public List<Flight> findAllFlights() {
		Criteria criteria = createEntityCriteria();
		return (List<Flight>) criteria.list();
	}

	@Override
	public List<Flight> findAllFlightsAvailable() {
		List<Integer> list = getActivesFlights();
		ArrayList<Flight> result = new ArrayList<Flight>();
		for (Integer id : list) {
			Flight findById = findById(id);
			result.add(findById);
		}
		return result;
	}
	
	@Override
	public boolean isActiveFlight(Integer id) {
		List<Integer> activesFlights = getActivesFlights();
		return activesFlights.contains(id);
	}

	@SuppressWarnings("unchecked")
	private List<Integer> getActivesFlights() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		String now = formatter.format(calendar.getTime());

		String queryString = 
				
				"select pas.id_flight from "
				+ "(select id_flight, capacity from plane inner join flight on plane.id_plane = flight.plane and time_init > '"+ now + "') as cap "
				+ "inner join"
				+ "(select id_flight, count(flight) as total from purchase right join flight on purchase.flight = flight.id_flight group by id_flight) as pas "
				+ "on cap.id_flight = pas.id_flight and pas.total < cap.capacity";

		Query q = getSession().createSQLQuery(queryString);
		List<Integer> list = (List<Integer>) q.list();
		return list;
	}

	@Override
	public void saveFlight(Flight flight) {
		persist(flight);
	}

	@Override
	public Flight findById(int id) {
		return getByKey(id);
	}

	@Override
	public void deleteFlight(Integer id) {
		Flight flight = getByKey(id);
		if (flight != null)
			delete(flight);
	}

	@Override
	public boolean validateAvailabilityPlane(Flight flight, Calendar init, Calendar end) {
		String initDate = formatter.format(init.getTime());
		String endDate = formatter.format(end.getTime());

		String queryString = "select count(*) from flight where (time_init between '" + initDate + "' and '" + endDate
				+ "'" + " or time_end between '" + initDate + "' and '" + endDate + "') and plane = "
				+ flight.getPlane().getIdPlane();

		if (flight.getIdFlight() != null) {
			String compleQuery = " and id_flight = " + flight.getIdFlight();
			queryString = queryString + compleQuery;
			Query q = getSession().createSQLQuery(queryString);
			BigInteger uniqueResult = (BigInteger) q.uniqueResult();
			return (uniqueResult.intValue() == 1);
		}

		Query q = getSession().createSQLQuery(queryString);
		BigInteger uniqueResult = (BigInteger) q.uniqueResult();
		return (uniqueResult.intValue() == 0);
	}

	@Override
	public void savePurchase(Purchase purchase) {
		getSession().persist(purchase);
	}

}
