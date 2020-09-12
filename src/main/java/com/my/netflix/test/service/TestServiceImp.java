package com.my.netflix.test.service;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TestServiceImp implements TestService {
	
	@Override
	public void slick(ModelAndView mav) {
		String check = "slick slider 예시 페이지입니다.";
		
		mav.addObject("check", check);
		mav.setViewName("testing/slick.hm");
	}
	
	@Override
	public void customSlider(ModelAndView mav) {
		String check = "slider Custom 페이지입니다.";
		
		mav.addObject("check", check);
		mav.setViewName("testing/customSlider.hm");
	}
	
	@Override
	public void netflixSlider(ModelAndView mav) {
		String check = "netflix slider 예시 페이지입니다.";
		
		mav.addObject("check", check);
		mav.setViewName("testing/netflix.hm");
	}
	
}
