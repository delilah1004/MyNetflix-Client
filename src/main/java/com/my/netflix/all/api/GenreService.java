package com.my.netflix.all.api;

import java.util.ArrayList;

import com.my.netflix.all.model.Genre;

public interface GenreService {
	
	// TV

	public ArrayList<Genre> getTVGenres();
	
	public ArrayList<String> getTVGenreNames();
	
	public int getTVGenreId(String genreName);
	
	public String getTVGenreName(int genreId);
	
	// 영화
	
	public ArrayList<Genre> getMovieGenres();
	
	public ArrayList<String> getMovieGenreNames();
	
	public int getMovieGenreId(String genreName);
	
	public String getMovieGenreName(int genreId);
}
