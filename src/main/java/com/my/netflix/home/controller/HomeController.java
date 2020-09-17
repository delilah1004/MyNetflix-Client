package com.my.netflix.home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.netflix.home.service.HomeService;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("home");

		ModelAndView mav = new ModelAndView();
		
		homeService.main(mav);

		return mav;
	}
	
	
}
