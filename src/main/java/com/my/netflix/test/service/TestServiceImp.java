package com.my.netflix.test.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.my.netflix.home.api.HomeAPIService;
import com.my.netflix.model.TVProgram;

@Component
public class TestServiceImp implements TestService {
	
	@Autowired
	HomeAPIService homeAPIService;
	
	@Override
	public void slick(ModelAndView mav) {
		String check = "slick slider 예시 페이지입니다.";
		
		mav.addObject("check", check);
		mav.setViewName("testing/slick.hm");
	}
	
	@Override
	public void slickTest(ModelAndView mav) {
		String check = "slick slider Custom Test 페이지입니다.";
		
		mav.addObject("check", check);
		
		ArrayList<TVProgram> array = homeAPIService.getBestPopularTVPrograms();
		
		mav.addObject("array", array);
		
		mav.setViewName("testing/slickTest.hm");
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
