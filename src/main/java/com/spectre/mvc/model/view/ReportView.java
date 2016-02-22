package com.spectre.mvc.model.view;

import com.spectre.mvc.model.Plane;

public class ReportView {
	private String start;
	private String end;
	private Plane plane;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}
	
	public ReportView() {
	}

	public ReportView(String start, String end, Plane plane) {
		super();
		this.start = start;
		this.end = end;
		this.plane = plane;
	}

}