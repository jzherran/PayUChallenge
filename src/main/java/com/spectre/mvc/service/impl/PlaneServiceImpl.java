package com.spectre.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spectre.mvc.dao.PlaneDao;
import com.spectre.mvc.model.Plane;
import com.spectre.mvc.service.PlaneService;

@Service("planeService")
@Transactional
public class PlaneServiceImpl implements PlaneService {

	@Autowired
	private PlaneDao dao;
	
	
	@Override
	public List<Plane> findAllPlanes() {
		return dao.findAllPlanes();
	}

	@Override
	public void savePlane(Plane plain) {
		dao.savePlane(plain);
	}

	@Override
	public Plane findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void updatePlane(Plane plane) {
		Plane entity = dao.findById(plane.getIdPlane());
		if(entity != null) {
			entity.setCapacity(plane.getCapacity());
			entity.setEnrollment(plane.getEnrollment());
			entity.setManufacturer(plane.getManufacturer());
			entity.setModel(plane.getModel());
		}
	}

	@Override
	public void deletePlaneById(Integer id) {
		dao.deletePlane(id);
	}
	
	@Override
	public Plane findPlaneByEnrollment(String enrollment) {
		return dao.findPlainByEnrollment(enrollment);
	}
	
	@Override
	public boolean isEnrollmentUnique(Integer id, String enrollment) {
		Plane plane = findPlaneByEnrollment(enrollment);
		return ( plane == null || ((id != null) && (plane.getIdPlane() == id)));
	}

}
