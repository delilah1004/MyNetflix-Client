package com.my.netflix.tv.model;

import java.util.ArrayList;

import com.my.netflix.all.model.Genre;

public class TVProgramPreview {

	// tv_id
	private long id;

	// poster URI
	private String posterPath;

	// 제목
	private String name;

	// 첫 방영일
	private String firstAirDate;

	// 마지막 방영일
	private String lastAirDate;

	// 장르
	private ArrayList<Genre> genres;

	public TVProgramPreview() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public String getFirstAirDate() {
		return firstAirDate;
	}

	public void setFirstAirDate(String firstAirDate) {
		this.firstAirDate = firstAirDate;
	}

	public String getLastAirDate() {
		return lastAirDate;
	}

	public void setLastAirDate(String lastAirDate) {
		this.lastAirDate = lastAirDate;
	}

	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<Genre> genres) {
		this.genres = genres;
	}

}
