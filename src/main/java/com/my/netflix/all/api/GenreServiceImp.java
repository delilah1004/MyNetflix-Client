package com.my.netflix.all.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.my.netflix.all.model.Genre;
import com.my.netflix.aop.Reader;
import com.my.netflix.aop.StaticData;

@Component
public class GenreServiceImp extends Reader implements GenreService {

	@Autowired
	AllService allService;

	private String url = StaticData.API_MAIN_URL;

	// TV 프로그램의 모든 장르 객체 반환
	public ArrayList<Genre> getTVGenres() {

		ArrayList<Genre> genres = new ArrayList<Genre>();

		url += "/genre/tv/list";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;

		JsonArray array = getGenres(url);

		for (JsonElement element : array) {
			
			Genre genre = new Genre();
			
			genre.setId(element.getAsJsonObject().get("id").getAsInt());
			genre.setName(element.getAsJsonObject().get("name").getAsString());
			
			genres.add(genre);
		}

		return genres;
	}

	// TV 프로그램의 모든 장르명 반환
	public ArrayList<String> getTVGenreNames() {

		ArrayList<String> genreNames = new ArrayList<String>();

		url += "/genre/tv/list";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;

		JsonArray genres = getGenres(url);

		for (JsonElement element : genres) {
			genreNames.add(element.getAsJsonObject().get("name").getAsString());
		}

		return genreNames;
	}

	// 장르명으로 장르 id 반환
	public int getTVGenreId(String genreName) {

		int genreId = 0;

		url += "/genre/tv/list";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;

		JsonArray genres = getGenres(url);

		for (JsonElement element : genres) {
			if (genreName.equals(element.getAsJsonObject().get("name").getAsString())) {
				genreId = element.getAsJsonObject().get("id").getAsInt();
				break;
			}
		}

		return genreId;
	}

	// 장르 id 로 장르명 반환
	public String getTVGenreName(int genreId) {

		String genreName = null;

		url += "/genre/tv/list";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;

		JsonArray genres = getGenres(url);

		for (JsonElement element : genres) {
			if (genreId == element.getAsJsonObject().get("id").getAsInt()) {
				genreName = element.getAsJsonObject().get("name").getAsString();
				break;
			}
		}

		return genreName;
	}
	
	public ArrayList<Genre> getMovieGenres() {
		
		ArrayList<Genre> genres = new ArrayList<Genre>();

		url += "/genre/movie/list";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;

		JsonArray array = getGenres(url);

		for (JsonElement element : array) {
			
			Genre genre = new Genre();
			
			genre.setId(element.getAsJsonObject().get("id").getAsInt());
			genre.setName(element.getAsJsonObject().get("name").getAsString());
			
			genres.add(genre);
		}

		return genres;
	}

	@Override
	public ArrayList<String> getMovieGenreNames() {

		ArrayList<String> genreNames = new ArrayList<String>();

		url += "/genre/movie/list";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;

		JsonArray genres = getGenres(url);

		for (JsonElement element : genres) {
			genreNames.add(element.getAsJsonObject().get("name").getAsString());
		}

		return genreNames;
	}

	@Override
	public int getMovieGenreId(String genreName) {

		int genreId = 0;

		url += "/genre/movie/list";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;

		JsonArray genres = getGenres(url);

		for (JsonElement element : genres) {
			if (genreName.equals(element.getAsJsonObject().get("name").getAsString())) {
				genreId = element.getAsJsonObject().get("id").getAsInt();
				break;
			}
		}

		return genreId;
	}

	// 장르 id 로 장르명 반환
	@Override
	public String getMovieGenreName(int genreId) {

		String genreName = null;

		url += "/genre/movie/list";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;

		JsonArray genres = getGenres(url);

		for (JsonElement element : genres) {
			if (genreId == element.getAsJsonObject().get("id").getAsInt()) {
				genreName = element.getAsJsonObject().get("name").getAsString();
				break;
			}
		}

		return genreName;
	}

}
