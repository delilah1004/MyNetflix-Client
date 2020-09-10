package com.my.netflix.main.service;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MainServiceImp implements MainService {
	
	@Override
	public void main(ModelAndView mav) {
		
		String check = "Home 메인페이지입니다.";
		
		mav.addObject("check", check);
		mav.setViewName("home/main");
	}
	
}
