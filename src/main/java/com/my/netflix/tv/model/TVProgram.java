package com.my.netflix.tv.model;

import java.util.ArrayList;

import com.my.netflix.all.model.Genre;

public class TVProgram {

	// tv_id
	private long id;

	// poster URI
	private String posterPath;

	// backdrop URI
	private String backdropPath;

	// 제목
	private String name;

	// 첫 방영일
	private String firstAirDate;

	// 마지막 방영일
	private String lastAirDate;

	// 장르
	private ArrayList<Genre> genres;

	// season
	private ArrayList<Season> seasons;

	// 프로그램 개요
	private String overview;

	// 영상 스트리밍 URL
	private String homepage;

	// 종영 여부 - Ended, Returning Series
	private String status;

	public TVProgram() {
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

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ArrayList<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(ArrayList<Season> seasons) {
		this.seasons = seasons;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
