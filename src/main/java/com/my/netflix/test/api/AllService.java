package com.my.netflix.test.api;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.my.netflix.home.model.TVProgramPreview;
import com.my.netflix.model.TVProgram;

public interface AllService {
    
    // 영화
	/*
	 * public ArrayList<Long> getAllMovieIds();
	 * 
	 * public JsonObject getMovieById(long id);
	 */

    //public ArrayList<Movie> getMovieList(ArrayList<Long> movieIdList);
    

    // TV 프로그램
    public ArrayList<Long> getAllTVIds();

    public JsonObject getTVById(long id);

    public JsonArray getPopularTVProgramIdList(int pageNumber);

    public ArrayList<TVProgram> getTVProgramList(ArrayList<Long> tvIdList);
    
}
