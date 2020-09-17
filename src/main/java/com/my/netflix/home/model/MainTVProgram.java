package com.my.netflix.home.model;

public class MainTVProgram {
	
	// tv_id
	private long id;
	
	// poster URI
	private String posterPath;

	public MainTVProgram() {
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
