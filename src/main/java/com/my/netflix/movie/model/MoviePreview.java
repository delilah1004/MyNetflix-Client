package com.my.netflix.movie.model;

import java.util.ArrayList;

import com.my.netflix.all.model.Genre;

public class MoviePreview {
	
	// movie_id
	private long id;

	// poster URI
	private String posterPath;

	// 제목
	private String title;

	// 개봉일
	private String releaseDate;

	// 영상 길이
	private int runtime;

	// 장르
	private ArrayList<Genre> genres;

	public MoviePreview() {
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

}
