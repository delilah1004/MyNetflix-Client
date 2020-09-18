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
import com.my.netflix.movie.model.Movie;
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
		
		String check = "영화 메인페이지입니다.";
		
		mav.addObject("check", check);
		
		// 페이지 번호, 검색 분류, 총 데이터 개수 초기화
		int pageNumber = 1;
		int condition = 0;
		int movieTotalCount = 0;
		
		// 페이지 번호가 존재하면 갱신
		if (request.getParameter("pageNumber") != null)
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		// 현재 페이지 번호 (기본값 : 1)
		mav.addObject("pageNumber", pageNumber);
		
		// 검색 분류값이 존재하면 갱신
		if (request.getParameter("condition") != null)
			condition = Integer.parseInt(request.getParameter("condition"));
		
		// 검색 분류 인덱스 (인기 내림차순 : 0 / 인기 오름차순 : 1 / 상영일 내림차순 : 2 / 상영일 오름차순 : 3 / 장르별 검색 : 4 / 연도별 검색 : 5)
		mav.addObject("condition", condition);
		
		ArrayList<MoviePreview> array = new ArrayList<MoviePreview>();
		
		switch (condition) {
		
		// 인기 내림차순
		case 0 :
			
			array = movieAPI.getPopularDescMovies(pageNumber);
			movieTotalCount = movieAPI.getCountPage(pageNumber, condition);
			break;
			
		// 인기 오름차순
		case 1 :
			
			array = movieAPI.getPopularAscMovies(pageNumber);
			movieTotalCount = movieAPI.getCountPage(pageNumber, condition);
			break;
			
		// 최신순
		case 2 :
			
			array = movieAPI.getLatestMovies(pageNumber);
			movieTotalCount = movieAPI.getCountPage(pageNumber, condition);
			break;
			
		// 오래된순
		case 3 :
			
			array = movieAPI.getOldestMovies(pageNumber);
			movieTotalCount = movieAPI.getCountPage(pageNumber, condition);
			break;
						
		// 장르별 검색
		case 4:
			
			String genreName = null;
			
			// 장르명 리스트 장르 Id 리스트로 변경
			if (request.getParameter("genre") != null) {
				genreName = request.getParameter("genre");
			}
			
			int genreId = (genreService.getMovieGenreId(genreName));
			
			array = movieAPI.getMoviesByGenreIds(pageNumber, genreId);
			movieTotalCount = movieAPI.getCountPage(pageNumber, genreId);
			
			break;
			
		case 5:
			
			String year = request.getParameter("year");
			
			array = movieAPI.getMoviesByYear(pageNumber, year);
			movieTotalCount = movieAPI.getCountPage(pageNumber, year);
			
			mav.addObject("year", year);
			
			break;
			
		}
		
		// 화면에 뿌려줄 MoviePreview 객체 리스트
		mav.addObject("array", array);
		
		// MoviePreview 객체의 총 개수
		mav.addObject("movieTotalCount", movieTotalCount);
		
		// 한 페이지에 뿌려줄 MoviePreview 객체의 개수
		mav.addObject("movieBlockCount", StaticData.count);
		
		ArrayList<String> genres = genreService.getMovieGenreNames();

		// 장르별 검색시 뿌려줄 모든 장르의 데이터
		mav.addObject("genres", genres);
		
		mav.setViewName("movie/main.hm");
	}

	@Override
	public void movieFullView(ModelAndView mav) {
		
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String check = "영화 상세페이지입니다.";
		
		mav.addObject("check", check);

		long movieId = Long.parseLong(request.getParameter("movieId"));
		
		Movie movie = movieAPI.getMovieById(movieId);
		
		mav.addObject("movie", movie);
		
		mav.setViewName("movie/fullView.hm");
	}
}
