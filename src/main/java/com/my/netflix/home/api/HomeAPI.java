package com.my.netflix.home.api;

import java.util.ArrayList;

import com.my.netflix.model.TVProgram;

public interface HomeAPI {
	
	public ArrayList<TVProgram> getBestPopularTVPrograms();
}
