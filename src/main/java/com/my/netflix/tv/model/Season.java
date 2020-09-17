package com.my.netflix.tv.model;

public class Season {

	// 시즌 Id
	private long id;

	// 시즌 대표 이미지
	private String posterPath;

	// 시즌 제목
	private String name;

	// 시즌 시작일
	private String airDate;

	// 시즌에 포함된 에피소드 개수
	private int episodeCount;

	public Season() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAirDate() {
		return airDate;
	}

	public void setAirDate(String airDate) {
		this.airDate = airDate;
	}

	public int getEpisodeCount() {
		return episodeCount;
	}

	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
	}

}
