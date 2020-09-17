package com.my.netflix.movie.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.netflix.all.api.AllService;
import com.my.netflix.all.model.Genre;
import com.my.netflix.aop.StaticData;
import com.my.netflix.movie.model.MoviePreview;

@Component
public class MovieAPIService implements MovieAPI {

	@Autowired
	AllService allService;

	// 한 페이지에 띄울 TV Program, 영화의 개수
	public static final int count = StaticData.count;

	// id 로 MoviePreview 객체 반환
	public MoviePreview getMovieById(long id) {

		// 반환값을 담을 Movie 객체 선언
		MoviePreview movie = new MoviePreview();

		ArrayList<Genre> genres = null;
		Genre genre = null;

		try {

			JsonObject mv = allService.getMovieJsonById(id);

			// movie_id
			movie.setId(mv.get("id").getAsLong());

			// 포스터 URI
			try {
				movie.setPosterPath(StaticData.API_IMAGE_URL + mv.get("poster_path").getAsString());
			} catch (Exception e) {
				movie.setPosterPath(StaticData.EMPTY_IMAGE_URL);
			}

			// 제목
			movie.setTitle(mv.get("title").getAsString());

			// 개봉일
			try {
				movie.setReleaseDate(mv.get("release_date").getAsString());
			} catch (Exception e) {
				movie.setReleaseDate(null);
			}

			// 영상 길이
			try {
				movie.setRuntime(mv.get("runtime").getAsInt());
			} catch (Exception e) {
				movie.setRuntime(0);
			}

			// 장르
			genres = new ArrayList<Genre>();

			for (JsonElement element : mv.get("genres").getAsJsonArray()) {
				genre = new Genre();
				genre.setId(element.getAsJsonObject().get("id").getAsInt());
				genre.setName(element.getAsJsonObject().get("name").getAsString());
				genres.add(genre);
			}

			movie.setGenres(genres);

		} catch (Exception e) {
			System.out.println(id);
			e.printStackTrace();
		}

		return movie;
	}

	// movieIdList 에 포함된 영화들의 정보 리스트 반환
	public ArrayList<MoviePreview> getMovieList(ArrayList<Long> movieIdList) {

		// 반환값을 담을 Movie 리스트 선언
		ArrayList<MoviePreview> movies = new ArrayList<MoviePreview>();

		for (long movieId : movieIdList) {
			movies.add(getMovieById(movieId));
		}

		return movies;
	}

	// 넷플릭스에서 방영되는 모든 TV Program 목록 반환
	public ArrayList<MoviePreview> getAllMoviesByPage(int pageNumber) {

		ArrayList<Long> allMovieIdList = allService.getIdListByFile(StaticData.MOVIE_ID_LIST_FILE_PATH);

		// 매칭된 movieId 만 담을 list 초기화
		ArrayList<Long> movieIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		// for (long movieId : movieIdList) {
		for (int i = startIndex; i < startIndex + count; i++) {
			movieIdList.add(allMovieIdList.get(i));
		}

		return getMovieList(movieIdList);
	}

	/* ------ 장르별 검색 -------- */

	// 장르 id 목록에 매칭되는 영화 목록 반환
	public ArrayList<MoviePreview> getMoviesByGenreIds(int pageNumber, ArrayList<Integer> genreIds) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&with_genres=";
		for (int genreId : genreIds) {
			url += genreId + "%2C";
		}
		url += "&with_networks=213";

		return getMovieList(allService.getIdListByUrl(url));
	}

	/* ------ 연도별 검색 -------- */

	// 연도별 영화 목록 반환
	public ArrayList<MoviePreview> getMoviesByYear(int pageNumber, String year) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/tv";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&first_air_date_year=" + year;
		url += "&with_networks=213";

		return getMovieList(allService.getIdListByUrl(url));
	}

	/* ------ 인기순 검색 -------- */

	// 인기순 - 내림차순 영화 목록 반환
	public ArrayList<MoviePreview> getPopularDescMovies(int pageNumber) {

		ArrayList<Long> popularDescMovieIdList = allService
				.getIdListByFile(StaticData.POPULAR_DESC_MOVIE_ID_LIST_FILE_PATH);

		// 매칭된 movieId 만 담을 list 초기화
		ArrayList<Long> movieIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			movieIdList.add(popularDescMovieIdList.get(i));
		}

		return getMovieList(movieIdList);
	}

	// 인기순 - 오름차순 영화 목록 반환
	public ArrayList<MoviePreview> getPopularAscMovies(int pageNumber) {

		// allMovieIdList 초기화
		ArrayList<Long> allMovieIdList = allService.getIdListByFile(StaticData.POPULAR_ASC_TV_ID_LIST_FILE_PATH);

		// 매칭된 movieId 만 담을 list 초기화
		ArrayList<Long> movieIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			movieIdList.add(allMovieIdList.get(i));
		}

		return getMovieList(movieIdList);
	}

	/* ------ 개봉일순 검색 -------- */

	// 최신순 영화 목록 반환
	public ArrayList<MoviePreview> getLatestMovies(int pageNumber) {

		// allMovieIdList 초기화
		ArrayList<Long> allMovieIdList = allService.getIdListByFile(StaticData.LATEST_MOVIE_ID_LIST_FILE_PATH);

		// 매칭된 movieId 만 담을 list 초기화
		ArrayList<Long> movieIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			movieIdList.add(allMovieIdList.get(i));
		}

		return getMovieList(movieIdList);
	}

	// 오래된순 영화 목록 반환
	public ArrayList<MoviePreview> getOldestMovies(int pageNumber) {

		// allMovieIdList 초기화
		ArrayList<Long> allMovieIdList = allService.getIdListByFile(StaticData.OLDEST_MOVIE_ID_LIST_FILE_PATH);

		// 매칭된 movieId 만 담을 list 초기화
		ArrayList<Long> movieIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			movieIdList.add(allMovieIdList.get(i));
		}

		return getMovieList(movieIdList);
	}

	/* ------------------ 공통 부분 -------------------- */

	// 넷플릭스에서 방영되는 모든 영화의 개수 반환
	public int getCountPage(int condition) {

		ArrayList<Long> movieIdList = new ArrayList<Long>();

		switch (condition) {
		case 0:
			movieIdList = allService.getIdListByFile(StaticData.POPULAR_DESC_MOVIE_ID_LIST_FILE_PATH);
			break;
		case 1:
			movieIdList = allService.getIdListByFile(StaticData.POPULAR_ASC_MOVIE_ID_LIST_FILE_PATH);
			break;
		case 2:
			movieIdList = allService.getIdListByFile(StaticData.LATEST_MOVIE_ID_LIST_FILE_PATH);
			break;
		case 3:
			movieIdList = allService.getIdListByFile(StaticData.OLDEST_MOVIE_ID_LIST_FILE_PATH);
			break;
		}

		return movieIdList.size();
	}

}
