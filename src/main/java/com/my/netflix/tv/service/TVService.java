package com.my.netflix.tv.service;

import org.springframework.web.servlet.ModelAndView;

public interface TVService {
	
	public void tvFullView(ModelAndView mav);
	
	public void setView(ModelAndView mav);
}
