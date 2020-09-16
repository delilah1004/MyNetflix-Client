package com.my.netflix.tv.service;

import org.springframework.web.servlet.ModelAndView;

public interface TVService {

	public void tvMain(ModelAndView mav);
	
	public void tvFullView(ModelAndView mav);
	
	public void setView(ModelAndView mav);
	
	public void genreView(ModelAndView mav);
	
	public void yearView(ModelAndView mav);
}
