package com.spectre.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spectre.mvc.model.Passenger;
import com.spectre.mvc.service.PassengerService;

@Controller
public class ApplicationController {

	@Autowired
	PassengerService service;
	
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String principalPage(ModelMap model) {
		List<Passenger> passengers = service.findAllPassengers();
		model.addAttribute("passengers", passengers);
		return "index";
	}
}
