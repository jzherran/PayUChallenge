package com.spectre.mvc.service;

import java.util.Calendar;
import java.util.List;

import com.spectre.mvc.model.Plane;
import com.spectre.mvc.model.Report;

public interface PlaneService {

	List<Plane> findAllPlanes();
	
	void savePlane(Plane plain);

	Plane findById(int id);

	void updatePlane(Plane plain);

	void deletePlaneById(Integer id);

	boolean isEnrollmentUnique(Integer id, String enrollment);

	Plane findPlaneByEnrollment(String enrollment);
	
	List<Report> createReportByDatesAndPlane(Integer idPlane, Calendar st, Calendar ed);
}
