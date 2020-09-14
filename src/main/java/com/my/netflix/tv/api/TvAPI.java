package com.my.netflix.tv.api;

import java.util.ArrayList;

import com.my.netflix.model.TVProgram;

public interface TvAPI {

	public ArrayList<TVProgram> getAllTVProgramsByPage(int pageNumber);
	
	public int getCountPage();
}
