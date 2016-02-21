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

import com.spectre.mvc.model.Airport;
import com.spectre.mvc.model.Route;
import com.spectre.mvc.service.AirportService;
import com.spectre.mvc.service.RouteService;

@Controller
@RequestMapping(value = "/route")
public class RouteController {

	@Autowired
	AirportService airportService;

	@Autowired
	RouteService routeService;

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
		fillModel(model, false, null);
		return "route";
	}
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveRoute(@Valid Route route, BindingResult result, ModelMap model) {
		
		fillModel(model, false, null);
		if (result.hasErrors()) {
			return "route";
		}
		if(route.getAirportOrigin().equals(route.getAirportDestination())){
			FieldError originError =new FieldError("route","airportOrigin",messageSource.getMessage("non.equals.airport", new String[] {route.getAirportOrigin().getName()}, Locale.getDefault()));
		    result.addError(originError);
			return "route";
		}
		
		routeService.saveRoute(route);
		return "redirect:/route/new";
	}

	private void fillModel(ModelMap model, boolean control, Integer id) {
		List<Route> routes = routeService.findAllRoutes();
		model.addAttribute("routes", routes);

		List<Airport> airports = airportService.findAllAirports();
		model.addAttribute("airports", airports);

		fillRoute(model, id);
		model.addAttribute("controlAction", control);
	}

	private void fillRoute(ModelMap model, Integer id) {
		Route route = new Route();
		if(id != null)
			route = routeService.findById(id);
		model.addAttribute("route", route);
		
	}
	
	@RequestMapping(value = { "/edit-{id}-route" }, method = RequestMethod.GET)
	public String principalRoute(@PathVariable Integer id, ModelMap model) {
		fillModel(model, true, id);
		return "route";
	}
}
