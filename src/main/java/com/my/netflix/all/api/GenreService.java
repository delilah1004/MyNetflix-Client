package com.my.netflix.all.api;

import java.util.ArrayList;

public interface GenreService {

	// TV 프로그램의 모든 장르명
	public ArrayList<String> getTVGenreNames();
	
	
	public int getTVGenreId(String genreName);
	
	public String getTVGenreName(int genreId);
	
	
	
	public ArrayList<String> getMovieGenreNames();
	
	public int getMovieGenreId(String genreName);
	
	public String getMovieGenreName(int genreId);
}