package com.my.netflix.home.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.my.netflix.home.api.HomeAPI;
import com.my.netflix.model.Movie;
import com.my.netflix.model.TVProgram;

@Component
public class HomeServiceImp implements HomeService {
	
	@Autowired
	HomeAPI homeAPI;
	
	@Override
	public void main(ModelAndView mav) {
		
		String check = "Home 메인페이지입니다.";
		
		mav.addObject("check", check);
		
		ArrayList<Movie> popularMovies = homeAPI.getBestPopularMovies();
		ArrayList<TVProgram> popularTVs = homeAPI.getBestPopularTVPrograms();
		
		mav.addObject("popularMovies", popularMovies);
		mav.addObject("popularTVs", popularTVs);
		
		mav.setViewName("home.hm");
	}
	
}
