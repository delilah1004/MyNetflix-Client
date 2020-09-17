package com.my.netflix.home.model;

public class MainMovie {

	// movie_id
	private long id;
	
	// poster URI
	private String posterPath;

	public MainMovie() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

}
