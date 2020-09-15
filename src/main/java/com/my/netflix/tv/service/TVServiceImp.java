package com.my.netflix.tv.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.my.netflix.aop.StaticData;
import com.my.netflix.model.TVProgram;
import com.my.netflix.tv.api.TvAPI;

@Component
public class TVServiceImp implements TVService {
	
	@Autowired
    TvAPI tvAPI;

	@Override
	public void tvMain(ModelAndView mav) {
		
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int pageNumber = 1;
		
		if(request.getParameter("pageNumber")!=null) pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		ArrayList<TVProgram> array = tvAPI.getAllTVProgramsByPage(pageNumber);
		int tvListCount = tvAPI.getCountPage();
		int listSize = StaticData.count;
		
		String check = "TV 프로그램 메인페이지입니다.";
		
		mav.addObject("check", check);
		
		mav.addObject("array", array);
		mav.addObject("tvListCount", tvListCount);
		mav.addObject("listSize", listSize);
		mav.addObject("pageNumber", pageNumber);
		
		mav.setViewName("tv/main.sh");
	}
	
	@Override
	public void tvFullView(ModelAndView mav) {

		String check = "TV 프로그램 상세페이지입니다.";
		
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		long tvId = Long.parseLong(request.getParameter("tvId"));
		
		TVProgram tv = tvAPI.getTVProgramById(tvId);
		
		mav.addObject("check", check);
		mav.addObject("tv", tv);
		
		mav.setViewName("tv/fullView.hm");
	}
	
}
