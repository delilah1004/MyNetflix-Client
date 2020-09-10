package com.my.netflix.tv.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.my.netflix.tv.service.TVService;

@Controller
public class TvController extends MultiActionController {
	
	@Autowired
	private TVService tvService;
	
	@RequestMapping(value = "/tv/main.mn", method = RequestMethod.GET)
	public ModelAndView tvMain(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("tv/main.mn");
		
		ModelAndView mav = new ModelAndView();
		tvService.main(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "/tv/fullView.mn", method = RequestMethod.GET)
	public ModelAndView fullView(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("tv/fullView.mn");
		
		ModelAndView mav = new ModelAndView();
		tvService.fullView(mav);
		
		return mav;
	}
	
}
