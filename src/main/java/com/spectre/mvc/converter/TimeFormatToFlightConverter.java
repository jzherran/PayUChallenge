package com.spectre.mvc.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TimeFormatToFlightConverter implements Converter<Object, Calendar> {

	private DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
	private DateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
	
	@Override
	public Calendar convert(Object element) {
		if(element instanceof Calendar)
    		return (Calendar) element;
    	else {
			Calendar convertCalendar = convertToTimestamp(element);
			return convertCalendar;
    	}
	}

	private Calendar convertToDate(Object element) {
		Calendar instance = Calendar.getInstance();
		try {
			Date d = sd.parse((String) element);
			instance.setTime(d);
			return instance;
		} catch (Exception e) {
			System.err.println("Ocurrio un error en el conversor de fechas");
		}
		return null;
	}
	
	private Calendar convertToTimestamp(Object element) {
		Calendar instance = Calendar.getInstance();
		try {
			Date d = sdf.parse((String) element);
			Calendar calendar = instance;
			calendar.setTime(d);
			return calendar;
		} catch (Exception e) {
			instance = convertToDate(element);
		}
		return instance;
	}
}