package com.spectre.mvc.dao;

import java.util.Calendar;
import java.util.List;

import com.spectre.mvc.model.Plane;
import com.spectre.mvc.model.Report;

public interface PlaneDao {

	List<Plane> findAllPlanes();

	void savePlane(Plane route);

	Plane findById(int id);

	void deletePlane(Integer id);

	Plane findPlainByEnrollment(String enrollment);

	List<Report> createReportByDatesAndPlane(Integer idPlane, Calendar st, Calendar ed);
}
