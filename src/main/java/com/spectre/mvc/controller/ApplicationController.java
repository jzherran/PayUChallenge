package com.spectre.mvc.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spectre.mvc.model.Plane;
import com.spectre.mvc.model.Report;
import com.spectre.mvc.model.view.ReportView;
import com.spectre.mvc.service.PlaneService;

@Controller
public class ApplicationController {
	
	@Autowired
	PlaneService planeService;
	
	@Autowired
	MessageSource messageSource;
	
	private DateFormat sd = new SimpleDateFormat("yyyy/MM/dd");

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String principalPage(ModelMap model) {
		return "index";
	}
	
	@RequestMapping(value = { "/detail" }, method = RequestMethod.GET)
	public String detailPage(ModelMap model) {
		List<Plane> planes = planeService.findAllPlanes();
		ReportView rv = new ReportView();
		
		model.addAttribute("planes", planes);
		model.addAttribute("report", rv);
		return "detail";
	}
	
	@RequestMapping(value = { "/detail/send" }, method = RequestMethod.POST)
	public String detailSendPage(@Valid ReportView report, ModelMap model) throws ParseException {
		if(!report.getEnd().isEmpty() && !report.getStart().isEmpty() && report.getPlane() != null) {
			List<Plane> planes = planeService.findAllPlanes();
			ReportView rv = new ReportView();
			model.addAttribute("planes", planes);
			model.addAttribute("report", rv);
			
			Calendar st = getCalendar(report.getStart());
			Calendar ed = getCalendar(report.getEnd());
			List<Report> reports = planeService.createReportByDatesAndPlane(report.getPlane().getIdPlane(), st, ed);
			model.addAttribute("reports", reports);
			return "detail";
		}
		return "redirect:/detail";
	}

	private Calendar getCalendar(String date) throws ParseException {
		Date d = sd.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar;
	}
}
