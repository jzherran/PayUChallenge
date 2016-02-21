package com.spectre.mvc.controller;

import java.util.List;
import java.util.Locale;

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

import com.spectre.mvc.model.Passenger;
import com.spectre.mvc.service.PassengerService;

@Controller
@RequestMapping(value = "/passenger")
public class PassengerController {

	@Autowired
	PassengerService service;
	
	@Autowired
	MessageSource messageSource;

	/**
	 * Load passenger in grid for you consult, edit or delete.
	 * 
	 * @param model
	 *            data to return view
	 * @return string redirection view
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String principalPassenger(ModelMap model) {

		List<Passenger> passengers = service.findAllPassengers();
		model.addAttribute("passengers", passengers);

		Passenger passenger = new Passenger();
		model.addAttribute("passenger", passenger);
		model.addAttribute("edit", false);
		return "passenger";
	}

	/**
	 * Save data for the Passenger
	 * @param passenger to save
	 * @param result validations with model
	 * @param model for load view
	 * @return Principal view for administration passengers
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String savePassenger(@Valid Passenger passenger, BindingResult result, ModelMap model) {
		
		List<Passenger> passengers = service.findAllPassengers();
		model.addAttribute("passengers", passengers);
		if (result.hasErrors()) {
			return "passenger";
		}
		if(!service.isPassengerINUnique(passenger.getIdPassenger(), passenger.getIdentificationNumber())){
			FieldError ssnError =new FieldError("passenger","identificationNumber",messageSource.getMessage("non.unique.idn", new String[]{passenger.getIdentificationNumber()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "passenger";
		}
		if(!service.isPassengerEmailUnique(passenger.getIdPassenger(), passenger.getEmail())){
			FieldError emailError =new FieldError("passenger","email",messageSource.getMessage("non.unique.email", new String[]{passenger.getEmail()}, Locale.getDefault()));
		    result.addError(emailError);
			return "passenger";
		}
		
		service.savePassenger(passenger);
		
		return "redirect:/passenger/new";
	}
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/edit-{id}-passenger" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable Integer id, ModelMap model) {
		
		List<Passenger> passengers = service.findAllPassengers();
		model.addAttribute("passengers", passengers);
		
		Passenger passenger = service.findById(id);
		model.addAttribute("passenger", passenger);
		model.addAttribute("controlAction", true);
		return "passenger";
	}
	
	/**
	 * 
	 * @param passenger
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/edit-{idn}-passenger" }, method = RequestMethod.POST)
	public String updatePassenger(@Valid Passenger passenger, BindingResult result, ModelMap model) {
		
		List<Passenger> passengers = service.findAllPassengers();
		model.addAttribute("passengers", passengers);
		
		if (result.hasErrors()) {
			model.addAttribute("controlAction", true);
			return "passenger";
		}
		if(!service.isPassengerINUnique(passenger.getIdPassenger(), passenger.getIdentificationNumber())){
			FieldError ssnError =new FieldError("passenger","identificationNumber",messageSource.getMessage("non.unique.idn", new String[]{passenger.getIdentificationNumber()}, Locale.getDefault()));
		    result.addError(ssnError);
		    model.addAttribute("controlAction", true);
			return "passenger";
		}
		if(!service.isPassengerEmailUnique(passenger.getIdPassenger(), passenger.getEmail())){
			FieldError emailError =new FieldError("passenger","email",messageSource.getMessage("non.unique.email", new String[]{passenger.getEmail()}, Locale.getDefault()));
		    result.addError(emailError);
		    model.addAttribute("controlAction", true);
			return "passenger";
		}
		
		service.updatePassenger(passenger);
		model.addAttribute("controlAction", false);
		
		return "redirect:/passenger/new";
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/delete-{id}-passenger" }, method = RequestMethod.GET)
	public String deletePassenger(@PathVariable Integer id) {
		service.deletePassengerById(id);
		return "redirect:/passenger/new";
	}
}
