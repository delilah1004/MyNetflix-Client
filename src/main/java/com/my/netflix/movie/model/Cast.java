package com.my.netflix.movie.model;

public class Cast {

	// 배우 id
	private long id;

	// 배우 사진
	private String profilePath;

	// 배우 이름
	private String name;

	// 극중 캐릭터 이름
	private String character;

	public Cast() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

}
