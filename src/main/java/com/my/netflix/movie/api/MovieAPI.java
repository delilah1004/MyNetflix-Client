package com.my.netflix.movie.api;

import java.util.ArrayList;

import com.my.netflix.movie.model.MoviePreview;

public interface MovieAPI {

	public ArrayList<MoviePreview> getAllMoviesByPage(int pageNumber);
	
	public MoviePreview getMovieById(long id);
	
	public ArrayList<MoviePreview> getMovieList(ArrayList<Long> tvIdList);

	/* ------ 장르별 검색 -------- */

	// 장르 id 목록에 매칭되는 영화 목록 반환
	public ArrayList<MoviePreview> getMoviesByGenreIds(int pageNumber, ArrayList<Integer> genreIds);

	/* ------ 연도별 검색 -------- */

	// 연도별 영화 목록 반환
	public ArrayList<MoviePreview> getMoviesByYear(int pageNumber, String year);

	/* ------ 인기순 검색 -------- */

	// 인기순 - 내림차순 영화 목록 반환
	public ArrayList<MoviePreview> getPopularDescMovies(int pageNumber);

	// 인기순 - 오름차순 영화 목록 반환
	public ArrayList<MoviePreview> getPopularAscMovies(int pageNumber);

	/* ------ 개봉일순 검색 -------- */

	// 최신순 영화 목록 반환
	public ArrayList<MoviePreview> getLatestMovies(int pageNumber);

	// 오래된순 영화 목록 반환
	public ArrayList<MoviePreview> getOldestMovies(int pageNumber);

	/* ------------------ 공통 부분 -------------------- */

	public int getCountPage(int condition);
}
