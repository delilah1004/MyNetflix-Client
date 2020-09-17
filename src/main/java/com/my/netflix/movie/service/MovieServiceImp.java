package com.my.netflix.movie.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.my.netflix.all.api.GenreService;
import com.my.netflix.aop.StaticData;
import com.my.netflix.movie.api.MovieAPI;
import com.my.netflix.movie.model.MoviePreview;

@Component
public class MovieServiceImp implements MovieService {
	
	@Autowired
	MovieAPI movieAPI;
	@Autowired
	GenreService genreService;
	
	@Override
	public void setView(ModelAndView mav) {
				
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int pageNumber = 1;
		int condition = 0;
		
		if (request.getParameter("pageNumber") != null)
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		if (request.getParameter("condition") != null)
			condition = Integer.parseInt(request.getParameter("condition"));
		
		ArrayList<MoviePreview> array = movieAPI.getAllMoviesByPage(pageNumber);
		
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
		case 4:

			String selectedGenres = null;

			if (request.getParameter("selectedGenres") != null) {
				selectedGenres = request.getParameter("selectedGenres");
			}

			String[] genreNames = selectedGenres.split(",");

			ArrayList<Integer> genreIds = new ArrayList<Integer>();

			for (String genreName : genreNames) {
				genreIds.add(genreService.getMovieGenreId(genreName));
			}

			array = movieAPI.getMoviesByGenreIds(pageNumber, genreIds);
			
			break;
			
		case 5:

			String year = request.getParameter("year");

			array = movieAPI.getMoviesByYear(pageNumber, year);
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
		
		ArrayList<String> genres = genreService.getMovieGenreNames();

		mav.addObject("genres", genres);
		
		mav.setViewName("movie/main.hm");
	}

	@Override
	public void movieFullView(ModelAndView mav) {
		
		String check = "영화 상세페이지입니다.";
		
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		long movieId = Long.parseLong(request.getParameter("movieId"));
		
		MoviePreview movie = movieAPI.getMovieById(movieId);

		mav.addObject("check", check);
		mav.addObject("movie", movie);
		
		mav.setViewName("movie/fullView.hm");
	}
}
