package com.spectre.mvc.controller;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spectre.mvc.model.Flight;
import com.spectre.mvc.model.Passenger;
import com.spectre.mvc.model.Plane;
import com.spectre.mvc.model.Purchase;
import com.spectre.mvc.model.Route;
import com.spectre.mvc.service.FlightService;
import com.spectre.mvc.service.PassengerService;
import com.spectre.mvc.service.PlaneService;
import com.spectre.mvc.service.RouteService;

@Controller
@RequestMapping(value = "/flight")
public class FlightController {

	@Autowired
	PlaneService planeService;

	@Autowired
	RouteService routeService;
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	PassengerService passengerService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String principalFlight(ModelMap model) {
		fillModel(model);
		fillFlight(model, null);
		fillPurchase(model);
		return "flight";
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveFlight(@Valid Flight flight, BindingResult result, ModelMap model) {
		
		fillModel(model);
		fillPurchase(model);
		
		if (result.hasErrors()) {
			return "flight";
		}
		if(flight.getDateCal() == null){
			FieldError dataError =new FieldError("flight","date",messageSource.getMessage("non.empty.data", null, null));
		    result.addError(dataError);
		    return "flight";
		}
		if(flight.getTimeInitCal() == null){
			FieldError dataError =new FieldError("flight","timeInit",messageSource.getMessage("non.empty.data", null, null));
		    result.addError(dataError);
		    return "flight";
		}
		calculateEstimateTime(flight);
		if(!flightService.validateAvailabilityPlane(flight, flight.getTimeInitCal(), flight.getTimeEndCal())){
			FieldError initTimeError =new FieldError("flight","timeInit",messageSource.getMessage("non.availability.time", null, null));
		    result.addError(initTimeError);
		    return "flight";
		}
		
		flightService.saveFlight(flight);
		
		return "redirect:/flight/new/";
	}

	private void calculateEstimateTime(Flight flight){
		Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(flight.getTimeInitCal().getTime().getTime());
        cal.add(Calendar.SECOND, (int) flight.getRoute().getTime());
		flight.setTimeEnd(cal);
	}
	
	@RequestMapping(value = { "/edit-{id}-flight" }, method = RequestMethod.GET)
	public String updateFlight(@PathVariable Integer id, ModelMap model) {
		fillModel(model);
		fillPurchase(model);
		fillFlight(model, id);
		model.addAttribute("controlAction", true);
		return "flight";
	}

	@RequestMapping(value = { "/edit-{id}-flight" }, method = RequestMethod.POST)
	public String updateFlight(@Valid Flight flight, BindingResult result, ModelMap model) {
		
		fillModel(model);
		fillPurchase(model);
		model.addAttribute("controlAction", true);
		
		if (result.hasErrors()) {
			return "flight";
		}
		if(flight.getDateCal() == null){
			FieldError dataError =new FieldError("flight","date",messageSource.getMessage("non.empty.data", null, null));
		    result.addError(dataError);
		    return "flight";
		}
		if(flight.getTimeInitCal() == null){
			FieldError dataError =new FieldError("flight","timeInit",messageSource.getMessage("non.empty.data", null, null));
		    result.addError(dataError);
		    return "flight";
		}
		calculateEstimateTime(flight);
		if(!flightService.validateAvailabilityPlane(flight, flight.getTimeInitCal(), flight.getTimeEndCal())){
			FieldError initTimeError =new FieldError("flight","timeInit",messageSource.getMessage("non.availability.time", null, null));
		    result.addError(initTimeError);
		    return "flight";
		}
		
		flightService.updateFlight(flight);
		model.addAttribute("controlAction", false);
		
		return "redirect:/flight/new/";
	}
	
	@RequestMapping(value = { "/new/purchase" }, method = RequestMethod.POST)
	public String savePurchase(@Valid Purchase purchase, BindingResult result, ModelMap model) {
		
		fillModel(model);
		fillFlight(model, null);
		
		if (result.hasErrors()) {
			return "flight";
		}
		if (!flightService.isActiveFlight(purchase.getFlight().getIdFlight())) {
			FieldError dataError = new FieldError("purchase", "flight",
					messageSource.getMessage("non.available.flight", null, null));
			result.addError(dataError);
			return "flight";
		}
		
		flightService.savePurchase(purchase);
		
		return "redirect:/flight/new/";
	}
	
	private void fillModel(ModelMap model) {
		List<Flight> flights = flightService.findAllFlights();
		model.addAttribute("flights", flights);
		
		List<Flight> flightsAvailable = flightService.findAllFlightsAvailable();
		model.addAttribute("flightsAvailable", flightsAvailable);
		
		List<Route> routes = routeService.findAllRoutes();
		model.addAttribute("routes", routes);
		
		List<Plane> planes = planeService.findAllPlanes();
		model.addAttribute("planes", planes);
		
		List<Passenger> passengers = passengerService.findAllPassengers();
		model.addAttribute("passengers", passengers);
	}
	
	private void fillPurchase(ModelMap model) {
		Purchase purchase = new Purchase();
		model.addAttribute("purchase", purchase);
	}
	
	private void fillFlight(ModelMap model, Integer id) {
		Flight flight = new Flight();
		if (id != null)
			flight = flightService.findById(id);
		model.addAttribute("flight", flight);
	}
}
