package com.my.netflix.home.model;

public class TVProgramPreview {

	// tv_id
	private long id;
	// 제목
	private String name;
	// 영상 길이
	private int episodeRunTime;
	// 장르
	private String genres;
	// poster URI
	private String posterPath;

	// 첫 방영일
	private String firstAirDate;

	public TVProgramPreview() {
	}

	public TVProgramPreview(long id, String name, int episodeRunTime, String genres, String posterPath, String firstAirDate) {
		this.id = id;
		this.name = name;
		this.episodeRunTime = episodeRunTime;
		this.genres = genres;
		this.posterPath = posterPath;
		this.firstAirDate = firstAirDate;
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

	public int getEpisodeRunTime() {
		return episodeRunTime;
	}

	public void setEpisodeRunTime(int episodeRunTime) {
		this.episodeRunTime = episodeRunTime;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
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

	@Override
	public String toString() {
		return "TVProgram [id=" + id + ", name=" + name + ", episodeRunTime=" + episodeRunTime + ", genres=" + genres
				+ ", posterPath=" + posterPath + ", firstAirDate=" + firstAirDate + "]";
	}

}
