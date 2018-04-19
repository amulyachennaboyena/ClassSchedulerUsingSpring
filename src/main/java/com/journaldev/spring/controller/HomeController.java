package com.journaldev.spring.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.journaldev.spring.model.User;
import com.journaldev.spring.model.*;

@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "index";
	}


	@RequestMapping(value = "/CSchedule", method = RequestMethod.GET)
	public String CancelClass(@Validated User user, Model model,HttpServletRequest request) {
		System.out.println("User Page Requested");
		model.addAttribute("date", request.getParameter("o1"));
		return "CSchedule";
	}

	@RequestMapping(value = "/CancelClass", method = RequestMethod.GET)
	public String user(@Validated ScheduleData data, Model model,HttpServletRequest reques) {
		String dat = reques.getParameter("date");
com.journaldev.spring.model.DBConnection db = new DBConnection();
		
	
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = null;
		try {
			date1 = sdf1.parse(dat);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		java.sql.Date sqlStartDate = new java.sql.Date(date1.getTime()); 
		System.out.println(sqlStartDate);
		List<ScheduleData> list = db.cancelCourseByDateOrTopicUnFinished(sqlStartDate, "cancelDate");
		ModelAndView modelAndView = new ModelAndView("CancelClass");
	    modelAndView.addObject("parameters", list);
	  
		System.out.println("User Page Requested");
		model.addAttribute("date", data.getSheduleDate());
		System.out.println(data.getSheduleDate());
		return "CancelClass";
	}
	@RequestMapping(value = "/IncompleteTopics", method = RequestMethod.GET)
	public String user2(@Validated ScheduleData data, Model model) {
		System.out.println("User Page Requested");
		model.addAttribute("date", data.getSheduleDate());
		System.out.println(data.getSheduleDate());
		return "IncompleteTopics";
	}
	
}
