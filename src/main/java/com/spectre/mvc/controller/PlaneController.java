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

import com.spectre.mvc.model.Plane;
import com.spectre.mvc.service.PlaneService;

@Controller
@RequestMapping(value = "/plane")
public class PlaneController {

	@Autowired
	PlaneService service;

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
	public String principalRoute(ModelMap model) {
		fillModel(model);
		fillRoute(model, null);
		model.addAttribute("controlAction", false);
		return "plane";
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveRoute(@Valid Plane plane, BindingResult result, ModelMap model) {
		
		fillModel(model);
		model.addAttribute("controlAction", false);
		
		if (result.hasErrors()) {
			return "plane";
		}
		if (service.isEnrollmentUnique(plane.getIdPlane(), plane.getEnrollment())) {
			FieldError enrollmentError = new FieldError("plane", "enrollment", messageSource
					.getMessage("non.equals.enrollment", new String[] { plane.getEnrollment() }, Locale.getDefault()));
			result.addError(enrollmentError);
		}

		service.savePlane(plane);
		return "redirect:/plane/new";
	}

	private void fillModel(ModelMap model) {
		List<Plane> planes = service.findAllPlanes();
		model.addAttribute("planes", planes);
	}

	private void fillRoute(ModelMap model, Integer id) {
		Plane plane = new Plane();
		if (id != null)
			plane = service.findById(id);
		model.addAttribute("plane", plane);

	}

	@RequestMapping(value = { "/edit-{id}-plane" }, method = RequestMethod.GET)
	public String updateRoute(@PathVariable Integer id, ModelMap model) {
		fillModel(model);
		fillRoute(model, id);
		model.addAttribute("controlAction", true);
		return "plane";
	}

	@RequestMapping(value = { "/edit-{id}-plane" }, method = RequestMethod.POST)
	public String updateRoute(@Valid Plane plane, BindingResult result, ModelMap model) {

		fillModel(model);
		model.addAttribute("controlAction", true);
		
		if (result.hasErrors()) {
			return "plane";
		}
		
		if (!service.isEnrollmentUnique(plane.getIdPlane(), plane.getEnrollment())) {
			FieldError enrollmentError = new FieldError("plane", "enrollment", messageSource
					.getMessage("non.equals.enrollment", new String[] { plane.getEnrollment() }, null));
			result.addError(enrollmentError);
			return "plane";
		}

		service.updatePlane(plane);

		return "redirect:/plane/new";
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/delete-{id}-plane" }, method = RequestMethod.GET)
	public String deleteRoute(@PathVariable Integer id) {
		service.deletePlaneById(id);
		return "redirect:/plane/new";
	}
}
