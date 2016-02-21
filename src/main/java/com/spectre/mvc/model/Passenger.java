package com.spectre.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "PASSENGER")
public class Passenger {

	@Id
	@Column(name = "ID_PASSENGER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPassenger;

	@NotBlank(message="Este campo no puede ser vacío")
	@NotNull(message="Este campo no puede ser nulo")
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@NotBlank(message="Este campo no puede ser vacío")
	@NotNull(message="Este campo no puede ser nulo")
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@NotBlank(message="Este campo no puede ser vacío")
	@NotNull(message="Este campo no puede ser nulo")
	@Column(name = "IDENTIFICATION_NUMBER", nullable = false, unique=true)
	private String identificationNumber;
	
	@NotBlank(message="Este campo no puede ser vacío")
	@NotNull(message="Este campo no puede ser nulo")
	@Column(name = "EMAIL", nullable = false, unique=true)
	private String email;

	@Column(name = "AGE", nullable = true)
	private Integer age;

	public int getIdPassenger() {
		return idPassenger;
	}

	public void setIdPassenger(int idPassenger) {
		this.idPassenger = idPassenger;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Passenger() {
		
	}
	
	public Passenger(int idPassenger, String firstName, String lastName, String identificationNumber, int age) {
		super();
		this.idPassenger = idPassenger;
		this.firstName = firstName;
		this.lastName = lastName;
		this.identificationNumber = identificationNumber;
		this.age = age;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Passenger))
			return false;
		Passenger other = (Passenger) obj;
		if (idPassenger != other.idPassenger && !identificationNumber.equals(other.identificationNumber)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + idPassenger + ", name=" + firstName + " " + lastName + ", identification="
				+ identificationNumber + ", age=" + age + "]";
	}

}
