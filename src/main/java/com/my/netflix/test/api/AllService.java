package com.my.netflix.test.api;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.my.netflix.model.Movie;
import com.my.netflix.model.TVProgram;

public interface AllService {
    
	// 영화
    public ArrayList<Long> getMovieIds(String filePath);

    public JsonObject getMovieJsonById(long id);

    public Movie getMovieById(long id);

    public ArrayList<Movie> getMovieList(ArrayList<Long> movieIdList);

    public JsonArray getPopularMovieIdList(int pageNumber);
    
    public JsonArray getNowPlayingMovieIdList(int pageNumber);
    

    // TV 프로그램
    public ArrayList<Long> getTVIds(String filePath);

    public JsonObject getTVJsonById(long id);

    public TVProgram getTVById(long id);

    public ArrayList<TVProgram> getTVProgramList(ArrayList<Long> tvIdList);

    public JsonArray getPopularTVProgramIdList(int pageNumber);

    public JsonArray getOnTheAirTVProgramIdList(int pageNumber);
    
}
