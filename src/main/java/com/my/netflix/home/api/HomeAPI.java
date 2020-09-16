package com.my.netflix.home.api;

import java.util.ArrayList;

import com.my.netflix.home.model.MainContent;
import com.my.netflix.model.Movie;
import com.my.netflix.model.TVProgram;

public interface HomeAPI {
	
//	public ArrayList<MainContent> getMainContents();
	
	public ArrayList<Movie> getBestPopularMovies();
	
	public ArrayList<Movie> getNowPlayingMovies();
	
	public ArrayList<TVProgram> getBestPopularTVPrograms();
	
	public ArrayList<TVProgram> getOnTheAirTVPrograms();
	
	
}
