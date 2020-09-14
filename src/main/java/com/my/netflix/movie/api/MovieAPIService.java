package com.my.netflix.movie.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.netflix.aop.StaticData;
import com.my.netflix.model.Movie;
import com.my.netflix.test.api.AllService;

@Component
public class MovieAPIService implements MovieAPI {
	
	@Autowired
	AllService allService;

	// 한 페이지에 띄울 TV Program, 영화의 개수
	public static final int count = StaticData.count;

	// 넷플릭스에서 방영되는 모든 TV Program 목록 반환
	public ArrayList<Movie> getAllMoviesByPage(int pageNumber) {
		ArrayList<Long> allMovieIdList = allService.getAllMovieIds();

		// 매칭된 movieId 만 담을 list 초기화
		ArrayList<Long> movieIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		// for (long movieId : movieIdList) {
		for (int i = startIndex; i < startIndex + count; i++) {
			movieIdList.add(allMovieIdList.get(i));
		}

		return allService.getMovieList(movieIdList);
	}

	// 넷플릭스에서 방영되는 모든 영화의 개수 반환
	public int getCountPage() {

		ArrayList<Long> allMovieIdList = allService.getAllMovieIds();
		
		return allMovieIdList.size();
	}
	
}
