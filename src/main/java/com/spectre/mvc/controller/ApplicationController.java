package com.spectre.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApplicationController {
	
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String principalPage(ModelMap model) {
		return "index";
	}
	
	@RequestMapping(value = { "/detail" }, method = RequestMethod.GET)
	public String detailPage(ModelMap model) {
		return "detail";
	}
}
