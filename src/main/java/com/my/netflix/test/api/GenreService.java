package com.my.netflix.test.api;

import java.util.ArrayList;

public interface GenreService {

	public ArrayList<String> getTVGenreNames();
	
	public int getTVGenreId(String genreName);
	
	public String getTVGenreName(int genreId);
}
