package com.my.netflix.all.api;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.my.netflix.home.model.MainContent;
import com.my.netflix.model.Movie;

public interface AllService {
    
	// 영화

    public JsonObject getMovieJsonById(long id);

    public Movie getMovieById(long id);

    public ArrayList<Movie> getMovieList(ArrayList<Long> movieIdList);
    
    public void getMainContentList(ArrayList<Long> mainContentIdList);
    

    public JsonObject getTVJsonById(long id);

//    public ArrayList<TVProgram> getTVProgramList(ArrayList<Long> tvIdList);

//    public JsonArray getPopularTVProgramIdList(int pageNumber);
//
//    public JsonArray getOnTheAirTVProgramIdList(int pageNumber);
    
    public ArrayList<Long> getIdListByFile(String filePath);
    
    public ArrayList<Long> getIdListByUrl(String url);
    
}
