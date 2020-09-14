package com.my.netflix.tv.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.my.netflix.model.TVProgram;
import com.my.netflix.tv.api.TvAPI;

@Component
public class TVServiceImp implements TVService {
	
	@Autowired
    TvAPI tvAPI;

	@Override
	public void tvMain(ModelAndView mav) {
		
		String check = "TV 프로그램 메인페이지입니다.";
		
		mav.addObject("check", check);
		
		
		ArrayList<TVProgram> array = tvAPI.getAllTVProgramsByPage(1);
		
		mav.addObject("array", array);
		
		mav.setViewName("tv/main.sh");
	}
	
	@Override
	public void tvFullView(ModelAndView mav) {

		String check = "TV 프로그램 상세페이지입니다.";
		
		mav.addObject("check", check);
		mav.setViewName("tv/fullView.hm");
	}
	
}
