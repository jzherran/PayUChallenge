package com.spectre.mvc.dao;

import java.util.List;

import com.spectre.mvc.model.Plane;

public interface PlaneDao {

	List<Plane> findAllPlanes();

	void savePlane(Plane route);

	Plane findById(int id);

	void deletePlane(Integer id);

	Plane findPlainByEnrollment(String enrollment);

}
