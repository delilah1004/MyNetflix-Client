package com.my.netflix.movie.api;

import java.util.ArrayList;

import com.my.netflix.all.model.Movie;

public interface MovieAPI {

	public ArrayList<Movie> getAllMoviesByPage(int pageNumber);
	
	public Movie getMovieById(long id);
	
	public ArrayList<Movie> getMovieList(ArrayList<Long> tvIdList);

	/* ------ 장르별 검색 -------- */

	// 장르 id 목록에 매칭되는 영화 목록 반환
	public ArrayList<Movie> getMoviesByGenreIds(int pageNumber, ArrayList<Integer> genreIds);

	/* ------ 연도별 검색 -------- */

	// 연도별 영화 목록 반환
	public ArrayList<Movie> getMoviesByYear(int pageNumber, String year);

	/* ------ 인기순 검색 -------- */

	// 인기순 - 내림차순 영화 목록 반환
	public ArrayList<Movie> getPopularDescMovies(int pageNumber);

	// 인기순 - 오름차순 영화 목록 반환
	public ArrayList<Movie> getPopularAscMovies(int pageNumber);

	/* ------ 개봉일순 검색 -------- */

	// 최신순 영화 목록 반환
	public ArrayList<Movie> getLatestMovies(int pageNumber);

	// 오래된순 영화 목록 반환
	public ArrayList<Movie> getOldestMovies(int pageNumber);

	/* ------------------ 공통 부분 -------------------- */

	public int getCountPage(int condition);
}
