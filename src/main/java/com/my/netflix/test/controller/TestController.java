package com.my.netflix.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.netflix.test.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	private TestService testService;

	@RequestMapping(value = "/testing/slick.mn", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("testing/slick.mn");
		
		ModelAndView mav = new ModelAndView();
		testService.slick(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "/testing/customSlider.mn", method = RequestMethod.GET)
	public ModelAndView customSlider(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("testing/customSlider.mn");
		
		ModelAndView mav = new ModelAndView();
		testService.customSlider(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "/testing/netflixSlider.mn", method = RequestMethod.GET)
	public ModelAndView netflixSlider(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("testing/netflixSlider.mn");
		
		ModelAndView mav = new ModelAndView();
		testService.netflixSlider(mav);
		
		return mav;
	}
	
}
