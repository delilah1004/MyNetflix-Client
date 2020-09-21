package com.my.netflix.all.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.netflix.aop.Reader;
import com.my.netflix.aop.StaticData;

@Component
public class AllServiceImp extends Reader implements AllService {

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
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;

		return url;
	}

	// Movie 인기 오름차순으로 검색하는 URL 반환
	public String searchMovieByPopularAsc(int pageNumber) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=popularity.asc";
		url += "&page=" + pageNumber;

		return url;
	}

	// Movie 최신순으로 검색하는 URL 반환
	public String searchMovieLatest(int pageNumber) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=first_air_date.desc";
		url += "&page=" + pageNumber;
		url += "&with_genres=";

		return url;
	}

	// Movie 오래된 순으로 검색하는 URL 반환
	public String searchMovieOldest(int pageNumber) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=first_air_date.asc";
		url += "&page=" + pageNumber;
		url += "&with_genres=";

		return url;
	}

	// Movie 장르로 검색하는 URL 반환
	public String searchMovieByGenreUrl(int pageNumber, int genreId) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&with_genres=";
		url += genreId;

		return url;
	}

	// Movie 연도로 검색하는 URL 반환
	public String searchMovieByYearUrl(int pageNumber, String year) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&first_air_date_year=" + year;

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
		url += "&with_genres=";
		url += "&with_networks=213";

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
		url += "&with_genres=";
		url += "&with_networks=213";

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
		url += "&with_genres=";
		url += genreId;
		url += "&with_networks=213";

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

		return url;
	}

	/* 공통 */

	// 파일에 담긴 id 값을 추출해서 IdList 로 반환
	public ArrayList<Long> getIdListByFile(String filePath) {

		FileReader fr = null;
		BufferedReader br = null;
		StringTokenizer st;

		String line;
		ArrayList<Long> idList = new ArrayList<Long>();

		try {
			fr = new FileReader(new File(filePath));
			br = new BufferedReader(fr);

			// file 한줄씩 읽기
			while ((line = br.readLine()) != null) {

				// StringTokenizer 에 한 줄 담기
				st = new StringTokenizer(line);

				// StringTokenizer 에 담긴 토큰을 list 에 추가
				while (st.hasMoreTokens()) {
					idList.add(Long.parseLong(st.nextToken()));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();

		} finally {

			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return idList;
	}

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
