package com.my.netflix.movie.model;

import java.util.ArrayList;

import com.my.netflix.all.model.Genre;

public class Movie {

	// movie_id
	private long id;

	// poster URI
	private String posterPath;

	// backdrop URI
	private String backdropPath;

	// 제목
	private String title;

	// 개봉일
	private String releaseDate;

	// 영상 길이
	private int runtime;

	// 장르
	private ArrayList<Genre> genres;

	// 출연 배우
	private ArrayList<Cast> casts;

	// 영화 개요
	private String overview;

	// 영상 스트리밍 URL
	private String homepage;

	// 종영 여부 - Rumored, Planned, In Production, Post Production, Released, Canceled
	private String status;

	public Movie() {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<Genre> genres) {
		this.genres = genres;
	}

	public ArrayList<Cast> getCasts() {
		return casts;
	}

	public void setCasts(ArrayList<Cast> casts) {
		this.casts = casts;
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
