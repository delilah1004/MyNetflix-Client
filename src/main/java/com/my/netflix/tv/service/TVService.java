package com.my.netflix.tv.service;

import org.springframework.web.servlet.ModelAndView;

public interface TVService {
	
	public void setView(ModelAndView mav);
	
	public void tvFullView(ModelAndView mav);
}
