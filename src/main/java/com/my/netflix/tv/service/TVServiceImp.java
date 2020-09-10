package com.my.netflix.tv.service;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TVServiceImp implements TVService {

	@Override
	public void main(ModelAndView mav) {
		
		String check = "TV 프로그램 메인페이지입니다.";
		
		mav.addObject("check", check);
		mav.setViewName("tv/main");
		
	}
	
	@Override
	public void fullView(ModelAndView mav) {

		String check = "TV 프로그램 상세페이지입니다.";
		
		mav.addObject("check", check);
		mav.setViewName("tv/fullView");
	}
	
}
