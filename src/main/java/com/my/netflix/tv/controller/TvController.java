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
	
	@RequestMapping(value = "/tv/setView.mn", method = RequestMethod.GET)
	public ModelAndView setView(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("tv/setView.mn");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("request", request);
		
		tvService.setView(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "/tv/fullView.mn", method = RequestMethod.GET)
	public ModelAndView tvFullView(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("tv/fullView.mn");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("request", request);
		
		tvService.tvFullView(mav);
		
		return mav;
	}
	
}
