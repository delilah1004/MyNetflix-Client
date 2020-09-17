package com.my.netflix.all.api;

import java.util.ArrayList;

import com.google.gson.JsonObject;

public interface AllService {

	/* ------ Movie ------- */

	public JsonObject getMovieJsonById(long id);

	/* ------ TV Program ------- */

	// tv_id 로 JsonObject 반환
	public JsonObject getTVJsonById(long id);

	// 인기 내림차순으로 검색하는 URL 반환
	public String searchTvByPopularDesc(int pageNumber);

	// 인기 오름차순으로 검색하는 URL 반환
	public String searchTvByPopularAsc(int pageNumber);

	// 최신순으로 검색하는 URL 반환
	public String searchTvLatest(int pageNumber);

	// 오래된 순으로 검색하는 URL 반환
	public String searchTvOldest(int pageNumber);

	// 장르로 검색하는 URL 반환
	public String searchTvByGenreUrl(int pageNumber, ArrayList<Integer> genreIds);

	// 연도로 검색하는 URL 반환
	public String searchTvByYearUrl(int pageNumber, String year);

	/* ------ 공통 ------- */

	public ArrayList<Long> getIdListByFile(String filePath);

	public ArrayList<Long> getIdListByUrl(String url);

}
