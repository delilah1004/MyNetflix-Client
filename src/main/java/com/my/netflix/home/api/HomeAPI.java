package com.my.netflix.home.api;

import java.util.ArrayList;

import com.my.netflix.all.model.Movie;
import com.my.netflix.all.model.TVProgram;
import com.my.netflix.home.model.MainContent;

public interface HomeAPI {
	
	public ArrayList<MainContent> getMainContents();
	
	public ArrayList<Movie> getBestPopularMovies();
	
	public ArrayList<Movie> getNowPlayingMovies();
	
	public ArrayList<TVProgram> getBestPopularTVPrograms();
	
	public ArrayList<TVProgram> getOnTheAirTVPrograms();
	
	
}
