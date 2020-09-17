package com.my.netflix.home.api;

import java.util.ArrayList;

import com.my.netflix.home.model.MainContent;
import com.my.netflix.home.model.MainMovie;
import com.my.netflix.home.model.MainTVProgram;

public interface HomeAPI {
	
	public ArrayList<MainContent> getMainContents();
	
	public ArrayList<MainMovie> getBestPopularMovies();
	
	public ArrayList<MainMovie> getNowPlayingMovies();
	
	public ArrayList<MainTVProgram> getBestPopularTVPrograms();
	
	public ArrayList<MainTVProgram> getOnTheAirTVPrograms();
	
}
