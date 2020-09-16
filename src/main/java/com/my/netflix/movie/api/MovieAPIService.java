package com.my.netflix.movie.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.netflix.all.api.AllService;
import com.my.netflix.aop.StaticData;
import com.my.netflix.model.Movie;

@Component
public class MovieAPIService implements MovieAPI {

	@Autowired
	AllService allService;

	// 한 페이지에 띄울 TV Program, 영화의 개수
	public static final int count = StaticData.count;

	// 넷플릭스에서 방영되는 모든 TV Program 목록 반환
	public ArrayList<Movie> getAllMoviesByPage(int pageNumber) {

		ArrayList<Long> allMovieIdList = allService.getIdListByFile(StaticData.MOVIE_ID_LIST_FILE_PATH);

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

	/* ------ 장르별 검색 -------- */

	// 장르 id 목록에 매칭되는 영화 목록 반환
	public ArrayList<Movie> getMoviesByGenreIds(int pageNumber, ArrayList<Integer> genreIds) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&with_genres=";
		for(int genreId : genreIds) {
			url += genreId + "%2C";
		}
		url += "&with_networks=213";

		return allService.getMovieList(allService.getIdListByUrl(url));
	}

	/* ------ 연도별 검색 -------- */

	// 연도별 영화 목록 반환
	public ArrayList<Movie> getMoviesByYear(int pageNumber, String year) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/tv";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&first_air_date_year=" + year;
		url += "&with_networks=213";
		
		return allService.getMovieList(allService.getIdListByUrl(url));
	}

	/* ------ 인기순 검색 -------- */

	// 인기순 - 내림차순 영화 목록 반환
	public ArrayList<Movie> getPopularDescMovies(int pageNumber) {

		ArrayList<Long> popularDescMovieIdList = allService.getIdListByFile(StaticData.POPULAR_DESC_MOVIE_ID_LIST_FILE_PATH);

		// 매칭된 movieId 만 담을 list 초기화
		ArrayList<Long> movieIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			movieIdList.add(popularDescMovieIdList.get(i));
		}

		return allService.getMovieList(movieIdList);
	}

	// 인기순 - 오름차순 영화 목록 반환
	public ArrayList<Movie> getPopularAscMovies(int pageNumber) {

		// allMovieIdList 초기화
		ArrayList<Long> allMovieIdList = allService.getIdListByFile(StaticData.POPULAR_ASC_TV_ID_LIST_FILE_PATH);

		// 매칭된 movieId 만 담을 list 초기화
		ArrayList<Long> movieIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			movieIdList.add(allMovieIdList.get(i));
		}

		return allService.getMovieList(movieIdList);
	}

	/* ------ 개봉일순 검색 -------- */

	// 최신순 영화 목록 반환
	public ArrayList<Movie> getLatestMovies(int pageNumber) {

		// allMovieIdList 초기화
		ArrayList<Long> allMovieIdList = allService.getIdListByFile(StaticData.LATEST_MOVIE_ID_LIST_FILE_PATH);

		// 매칭된 movieId 만 담을 list 초기화
		ArrayList<Long> movieIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			movieIdList.add(allMovieIdList.get(i));
		}

		return allService.getMovieList(movieIdList);
	}

	// 오래된순 영화 목록 반환
	public ArrayList<Movie> getOldestMovies(int pageNumber) {

		// allMovieIdList 초기화
		ArrayList<Long> allMovieIdList = allService.getIdListByFile(StaticData.OLDEST_MOVIE_ID_LIST_FILE_PATH);

		// 매칭된 movieId 만 담을 list 초기화
		ArrayList<Long> movieIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			movieIdList.add(allMovieIdList.get(i));
		}

		return allService.getMovieList(movieIdList);
	}

	/* ------------------ 공통 부분 -------------------- */

	// 넷플릭스에서 방영되는 모든 영화의 개수 반환
	public int getCountPage(int condition) {

		ArrayList<Long> movieIdList = new ArrayList<Long>();

		switch (condition) {
		case 0:
			movieIdList = allService.getIdListByFile(StaticData.POPULAR_DESC_MOVIE_ID_LIST_FILE_PATH);
			break;
		case 1:
			movieIdList = allService.getIdListByFile(StaticData.POPULAR_ASC_MOVIE_ID_LIST_FILE_PATH);
			break;
		case 2:
			movieIdList = allService.getIdListByFile(StaticData.LATEST_MOVIE_ID_LIST_FILE_PATH);
			break;
		case 3:
			movieIdList = allService.getIdListByFile(StaticData.OLDEST_MOVIE_ID_LIST_FILE_PATH);
			break;
		}

		return movieIdList.size();
	}

	// id로 Movie 객체 반환
	public Movie getMovieById(long id) {

		return allService.getMovieById(id);
	}

}
