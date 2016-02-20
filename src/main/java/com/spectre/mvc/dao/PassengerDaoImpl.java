package com.spectre.mvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.spectre.mvc.model.Passenger;

@Repository("passengerDao")
public class PassengerDaoImpl extends AbstractDao<Integer, Passenger> implements PassengerDao {

	public Passenger findById(int id) {
		return getByKey(id);
	}

	public void saveEmployee(Passenger employee) {
		persist(employee);
	}

	public void deleteEmployeeByIN(String in) {
		Query query = getSession().createSQLQuery("delete from Passenger where identificationNumber LIKE %:in%");
		query.setString("in", in);
		query.executeUpdate();
	}

	public Passenger findPassengerByIN(String identificationNumber) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("identificationNumber", identificationNumber));
		return (Passenger) criteria.uniqueResult();
	}

	@Override
	public void savePassenger(Passenger passenger) {
		persist(passenger);
	}

	@Override
	public void deletePassenger(Integer id) {
		Passenger passenger = getByKey(id);
		if(passenger != null)
			delete(passenger);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Passenger> findAllPassengers() {
		Criteria criteria = createEntityCriteria();
		return (List<Passenger>) criteria.list();
	}
}
