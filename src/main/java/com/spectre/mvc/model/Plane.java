package com.spectre.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "PLANE")
public class Plane {

	@Id
	@Column(name = "ID_PLANE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPlane;

	@Column(name = "MODEL", nullable = false)
	private int model;

	@Column(name = "CAPACITY", nullable = false)
	private int capacity;

	@NotBlank
	@Column(name = "MANUFACTURER", nullable = false)
	private String manufacturer;
	
	@NotBlank
	@Column(name = "ENROLLMENT", nullable = false)
	private String enrollment;

	public Plane() {

	}

	public Plane(int idPlane, int model, int capacity, String manufacturer, String enrollment) {
		super();
		this.idPlane = idPlane;
		this.model = model;
		this.capacity = capacity;
		this.manufacturer = manufacturer;
		this.enrollment = enrollment;
	}

	public int getIdPlane() {
		return idPlane;
	}

	public void setIdPlane(int idPlane) {
		this.idPlane = idPlane;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getEnrollment() {
		return enrollment;
	}
	
	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Plane))
			return false;
		Plane other = (Plane) obj;
		if (idPlane != other.getIdPlane()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Plane [id=" + idPlane + ", model=" + model + ", capacity=" + capacity + ", manufacturer=" + manufacturer
				+ ", enrollment=" + enrollment + "]";
	}
}