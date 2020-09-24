package com.my.netflix.all.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.netflix.aop.Reader;
import com.my.netflix.aop.StaticData;

@Component
public class AllServiceImp extends Reader implements AllService {
	
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	/* ------ Movie ------- */

	// movie_id 로 JsonObject 로 반환
	public JsonObject getMovieJsonById(long id) {

		String url = StaticData.API_MAIN_URL;
		url += "/movie/" + id;
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;

		getReader(url);

		return getJson();
	}

	// movie_id 로 JsonObject 반환 (배우 정보 포함)
	public JsonObject getMovieJsonWithCastsById(long id) {

		String url = StaticData.API_MAIN_URL;
		url += "/movie/" + id;
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&append_to_response=casts";

		getReader(url);

		return getJson();
	}

	// Movie 인기 내림차순으로 검색하는 URL 반환
	public String searchMovieByPopularDesc(int pageNumber) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&region=KR";
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&release_date.lte=" + format.format(new Date());

		return url;
	}

	// Movie 인기 오름차순으로 검색하는 URL 반환
	public String searchMovieByPopularAsc(int pageNumber) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&region=KR";
		url += "&sort_by=popularity.asc";
		url += "&page=" + pageNumber;
		url += "&release_date.lte=" + format.format(new Date());

		return url;
	}

	// Movie 최신순으로 검색하는 URL 반환
	public String searchMovieLatest(int pageNumber) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&region=KR";
		url += "&sort_by=first_air_date.desc";
		url += "&page=" + pageNumber;
		url += "&release_date.lte=" + format.format(new Date());

		return url;
	}

	// Movie 오래된 순으로 검색하는 URL 반환
	public String searchMovieOldest(int pageNumber) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&region=KR";
		url += "&sort_by=first_air_date.asc";
		url += "&page=" + pageNumber;
		url += "&release_date.lte=" + format.format(new Date());

		return url;
	}

	// Movie 장르로 검색하는 URL 반환
	public String searchMovieByGenreUrl(int pageNumber, int genreId) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&region=KR";
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&with_genres=" + genreId;
		url += "&release_date.lte=" + format.format(new Date());

		return url;
	}

	// Movie 연도로 검색하는 URL 반환
	public String searchMovieByYearUrl(int pageNumber, String year) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&region=KR";
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&primary_release_year=" + year;
		url += "&release_date.lte=" + format.format(new Date());

		return url;
	}

	/* ------ TV Program ------- */

	// tv_id 로 JsonObject 반환
	public JsonObject getTVJsonById(long id) {

		String url = StaticData.API_MAIN_URL;
		url += "/tv/" + id;
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;

		getReader(url);

		return getJson();
	}

	// TV Program 인기 내림차순으로 검색하는 URL 반환
	public String searchTvByPopularDesc(int pageNumber) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/tv";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&with_networks=213";
		url += "&first_air_date.lte=" + format.format(new Date());
		url += "&include_null_first_air_dates=false";

		return url;
	}

	// TV Program 인기 오름차순으로 검색하는 URL 반환
	public String searchTvByPopularAsc(int pageNumber) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/tv";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=popularity.asc";
		url += "&page=" + pageNumber;
		url += "&with_networks=213";
		url += "&first_air_date.lte=" + format.format(new Date());
		url += "&include_null_first_air_dates=false";

		return url;
	}

	// TV Program 최신순으로 검색하는 URL 반환
	public String searchTvLatest(int pageNumber) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/tv";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=first_air_date.desc";
		url += "&page=" + pageNumber;
		url += "&with_networks=213";
		url += "&first_air_date.lte=" + format.format(new Date());
		url += "&include_null_first_air_dates=false";

		return url;
	}

	// TV Program 오래된 순으로 검색하는 URL 반환
	public String searchTvOldest(int pageNumber) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/tv";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=first_air_date.asc";
		url += "&page=" + pageNumber;
		url += "&with_networks=213";
		url += "&first_air_date.lte=" + format.format(new Date());
		url += "&include_null_first_air_dates=false";

		return url;
	}

	// TV Program 장르로 검색하는 URL 반환
	public String searchTvByGenreUrl(int pageNumber, int genreId) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/tv";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&with_genres=" + genreId;
		url += "&with_networks=213";
		url += "&first_air_date.lte=" + format.format(new Date());
		url += "&include_null_first_air_dates=false";

		return url;
	}

	// TV Program 연도로 검색하는 URL 반환
	public String searchTvByYearUrl(int pageNumber, String year) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/tv";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&first_air_date_year=" + year;
		url += "&with_networks=213";
		url += "&first_air_date.lte=" + format.format(new Date());
		url += "&include_null_first_air_dates=false";

		return url;
	}

	/* 공통 */

	// url을 통해 Movie, TV Program 의 IdList 반환
	public ArrayList<Long> getIdListByUrl(String url) {

		ArrayList<Long> idList = new ArrayList<Long>();

		for (JsonElement jsonElement : getResults(url)) {
			idList.add(jsonElement.getAsJsonObject().get("id").getAsLong());
		}

		return idList;
	}

	// url 을 통해 장르의 IdList 반환
	public ArrayList<Integer> getGenreIdList(String url) {

		ArrayList<Integer> genreIdList = new ArrayList<Integer>();

		for (JsonElement jsonElement : getResults(url)) {
			genreIdList.add(jsonElement.getAsJsonObject().get("id").getAsInt());
		}

		return genreIdList;
	}

}
