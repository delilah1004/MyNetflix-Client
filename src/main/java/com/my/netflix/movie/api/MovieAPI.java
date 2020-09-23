package com.my.netflix.movie.api;

import java.util.ArrayList;

import com.my.netflix.movie.model.Movie;
import com.my.netflix.movie.model.MoviePreview;

public interface MovieAPI {

	/* --------------- 인기순 검색 ----------------- */

	// 인기순 - 내림차순 MoviePreview 목록 반환
	public ArrayList<MoviePreview> getPopularDescMovies(int pageNumber);

	// 인기순 - 오름차순 MoviePreview 목록 반환
	public ArrayList<MoviePreview> getPopularAscMovies(int pageNumber);

	/* ---------------- 개봉일순 검색 ---------------- */

	// 최신순 MoviePreview 목록 반환
	public ArrayList<MoviePreview> getLatestMovies(int pageNumber);

	// 오래된순 MoviePreview 목록 반환
	public ArrayList<MoviePreview> getOldestMovies(int pageNumber);
	
	/* -------------- 장르별 검색 ----------------- */
	
	// 장르 id 목록에 매칭되는 MoviePreview 목록 반환 (API)
	public ArrayList<MoviePreview> getMoviesByGenreIds(int pageNumber, int genreId);
	
	/* --------------- 연도별 검색 ----------------- */
	
	// 연도별 MoviePreview 목록 반환 (API)
	public ArrayList<MoviePreview> getMoviesByYear(int pageNumber, String year);
	
	/* ------------ 페이지 데이터 개수 반환 ------------- */

	public int getCountPage(int pageNumber, int condition);
	
	public int getCountPageByGenre(int pageNumber, int genreId);
	
	public int getCountPage(int pageNumber, String year);
	
	/* ----------------- 공통 부분 ------------------ */
	
	public MoviePreview getMoviePreviewById(long id);
	
	public ArrayList<MoviePreview> getMoviePreviewList(ArrayList<Long> movieIdList);
	
	public Movie getMovieById(long id);
	
	public ArrayList<Movie> getMovieList(ArrayList<Long> movieIdList);
}
