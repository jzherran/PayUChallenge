package com.spectre.mvc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.spectre.mvc.dao.AbstractDao;
import com.spectre.mvc.dao.PlaneDao;
import com.spectre.mvc.model.Plane;

@Repository("planeDao")
public class PlaneDaoImpl extends AbstractDao<Integer, Plane> implements PlaneDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Plane> findAllPlanes() {
		Criteria criteria = createEntityCriteria();
		return (List<Plane>)criteria.list();
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
		if(plane != null)
			delete(plane);
	}

	@Override
	public Plane findPlainByEnrollment(String enrollment) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("enrollment", enrollment));
		return (Plane) criteria.uniqueResult();
	}
}
