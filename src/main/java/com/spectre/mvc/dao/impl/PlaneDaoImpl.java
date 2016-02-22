package com.spectre.mvc.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.spectre.mvc.dao.AbstractDao;
import com.spectre.mvc.dao.PlaneDao;
import com.spectre.mvc.model.Flight;
import com.spectre.mvc.model.Plane;
import com.spectre.mvc.model.Report;

@Repository("planeDao")
public class PlaneDaoImpl extends AbstractDao<Integer, Plane> implements PlaneDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Plane> findAllPlanes() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("active", true));
		return (List<Plane>) criteria.list();
	}

	@Override
	public void savePlane(Plane plane) {
		persist(plane);
	}

	@Override
	public Plane findById(int id) {
		return getByKey(id);
	}

	@Override
	public void deletePlane(Integer id) {
		Plane plane = getByKey(id);
		if (plane != null)
			plane.setActive(false);
	}

	@Override
	public Plane findPlainByEnrollment(String enrollment) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("enrollment", enrollment));
		return (Plane) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Report> createReportByDatesAndPlane(Integer idPlane, Calendar st, Calendar ed) {
		String queryString = "select f from Purchase p "
				+ "inner join p.flight as f where f.plane.idPlane=:idPlane "
				+ "and p.flight.idFlight = f.idFlight "
				+ "and f.timeInit between :dateInit and :dateEnd";
		Query q = getSession().createQuery(queryString);
		q.setParameter("idPlane", idPlane);
		q.setParameter("dateInit", st);
		q.setParameter("dateEnd", ed);
		List<Flight> list = (List<Flight>) q.list();
		System.out.println(list);
		HashMap<Integer, Report> map = new HashMap<Integer, Report>();
		for (Flight flight : list) {
			Report report = map.get(flight.getRoute().getIdRoute());
			if (report != null) {
				report.setFlights(report.getFlights() + 1);
			} else {
				map.put(flight.getRoute().getIdRoute(), new Report(flight.getRoute(), 1, flight.getPurchases().size()));
			}
		}
		System.out.println(map);
		return new ArrayList<Report>(map.values());
	}
}
