package com.my.netflix.movie.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.my.netflix.aop.StaticData;
import com.my.netflix.model.Movie;
import com.my.netflix.movie.api.MovieAPI;

@Component
public class MovieServiceImp implements MovieService {
	
	@Autowired
	MovieAPI movieAPI;

	@Override
	public void movieMain(ModelAndView mav) {
				
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int pageNumber = 1;
		
		if(request.getParameter("pageNumber")!=null) pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		ArrayList<Movie> array = movieAPI.getAllMoviesByPage(pageNumber);
		
		int condition = 0;
		
		int movieListCount = movieAPI.getCountPage(condition);
		
		int listSize = StaticData.count;
		
		String check = "영화 메인페이지입니다.";

		mav.addObject("check", check);
		
		mav.addObject("array", array);
		mav.addObject("movieListCount", movieListCount);
		mav.addObject("listSize", listSize);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("condition", condition);
		
		mav.setViewName("movie/main.hm");
	}
	
	@Override
	public void setView(ModelAndView mav) {
				
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int pageNumber = 1;
		
		if(request.getParameter("pageNumber")!=null) pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		ArrayList<Movie> array = movieAPI.getAllMoviesByPage(pageNumber);		
		
		int condition = Integer.parseInt(request.getParameter("condition"));
		
		switch (condition) {
		case 0 :
			array = movieAPI.getPopularDescMovies(pageNumber);
			break;
		case 1 :
			array = movieAPI.getPopularAscMovies(pageNumber);
			break;
		case 2 :
			array = movieAPI.getLatestMovies(pageNumber);
			break;
		case 3 :
			array = movieAPI.getOldestMovies(pageNumber);
			break;
		}
		
		int movieListCount = movieAPI.getCountPage(condition);
		
		int listSize = StaticData.count;
		
		String check = "영화 메인페이지입니다.";

		mav.addObject("check", check);
		
		mav.addObject("array", array);
		mav.addObject("movieListCount", movieListCount);
		mav.addObject("listSize", listSize);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("condition", condition);
		
		mav.setViewName("movie/main.hm");
	}

	@Override
	public void movieFullView(ModelAndView mav) {
		
		String check = "영화 상세페이지입니다.";
		
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		long movieId = Long.parseLong(request.getParameter("movieId"));
		
		Movie movie = movieAPI.getMovieById(movieId);

		mav.addObject("check", check);
		mav.addObject("movie", movie);
		
		mav.setViewName("movie/fullView.hm");
	}
}
