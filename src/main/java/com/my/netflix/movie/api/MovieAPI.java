package com.my.netflix.movie.api;

import java.util.ArrayList;

import com.my.netflix.model.Movie;

public interface MovieAPI {

	public ArrayList<Movie> getAllMoviesByPage(int pageNumber);
	
	public int getCountPage();
}
