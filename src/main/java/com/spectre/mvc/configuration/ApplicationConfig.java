package com.spectre.mvc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.spectre.mvc.converter.SelectedToAirportConverter;
import com.spectre.mvc.converter.SelectedToFlightConverter;
import com.spectre.mvc.converter.SelectedToPassengerConverter;
import com.spectre.mvc.converter.SelectedToPlaneConverter;
import com.spectre.mvc.converter.SelectedToRouteConverter;
import com.spectre.mvc.converter.TimeFormatToFlightConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.spectre.mvc")
public class ApplicationConfig extends WebMvcConfigurerAdapter {

	@Autowired
    SelectedToAirportConverter selectedToAirportConverter;
	
	@Autowired
    SelectedToRouteConverter selectedToRouteConverter;
	
	@Autowired
    SelectedToPlaneConverter selectedToPlaneConverter;
	
	@Autowired
    SelectedToPassengerConverter selectedToPassengerConverter;
	
	@Autowired
    SelectedToFlightConverter selectedToFlightConverter;
	
	@Autowired
    TimeFormatToFlightConverter timeFormatToFlightConverter;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/theme/").setCachePeriod(31556926);
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(selectedToAirportConverter);
		registry.addConverter(selectedToRouteConverter);
		registry.addConverter(selectedToPlaneConverter);
		registry.addConverter(selectedToPassengerConverter);
		registry.addConverter(selectedToFlightConverter);
		registry.addConverter(timeFormatToFlightConverter);
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}
}
