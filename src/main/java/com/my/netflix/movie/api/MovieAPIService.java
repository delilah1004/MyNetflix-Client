package com.my.netflix.movie.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.netflix.all.api.AllService;
import com.my.netflix.all.model.Genre;
import com.my.netflix.aop.Reader;
import com.my.netflix.aop.StaticData;
import com.my.netflix.movie.model.Cast;
import com.my.netflix.movie.model.Movie;
import com.my.netflix.movie.model.MoviePreview;

@Component
public class MovieAPIService extends Reader implements MovieAPI {

	@Autowired
	AllService allService;

	// 한 페이지에 띄울 TV Program, 영화의 개수
	public static final int count = StaticData.count;

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

		return getMoviePreviewList(movieIdList);
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

		return getMoviePreviewList(movieIdList);
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

		return getMoviePreviewList(movieIdList);
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

		return getMoviePreviewList(movieIdList);
	}

	/* ------ 장르별 검색 -------- */

	// 장르 id 목록에 매칭되는 MoviePreviewList 반환 (API)
	public ArrayList<MoviePreview> getMoviesByGenreIds(int pageNumber, int genreId) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/movie";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&with_genres=";
		url += genreId;
		url += "&with_networks=213";

		return getMoviePreviewList(allService.getIdListByUrl(url));
	}

	// 장르 id 목록에 매칭되는 영화 목록 반환 (File)
	public ArrayList<MoviePreview> getMoviesByGenreIds(long lastId, int genreId) {

		ArrayList<Long> allMovieIdList = allService.getIdListByFile(StaticData.POPULAR_DESC_MOVIE_ID_LIST_FILE_PATH);

		// 매칭된 movie_id 만 담을 list 초기화
		ArrayList<Long> movieIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex;

		if (lastId == 0) {
			// 첫 번째 페이지면 0부터 시작
			startIndex = 0;
		} else {
			// 이전 페이지의 마지막 프로그램 다음 index 부터 검색
			startIndex = allMovieIdList.indexOf(lastId) + 1;
		}

		// 선택한 장르에 매칭되는 movie_id 리스트 생성 (최대 개수 = count)
		for (int i = startIndex; i < allMovieIdList.size(); i++) {

			JsonObject movie = allService.getMovieJsonById(allMovieIdList.get(i));

			// 해당 영화의 장르 중 검색할 장르가 포함되어 있는지 검사
			for (JsonElement element : movie.get("genres").getAsJsonArray()) {
				if (genreId == element.getAsJsonObject().get("id").getAsInt()) {
					movieIdList.add(allMovieIdList.get(i));
					break;
				}
			}

			// 한 페이지에 띄울 영화의 개수를 충족하면 종료
			if (movieIdList.size() == count)
				break;
		}

		return getMoviePreviewList(movieIdList);
	}

	/* ------ 연도별 검색 -------- */

	// 연도별 MoviePreview 목록 반환 (API)
	public ArrayList<MoviePreview> getMoviesByYear(int pageNumber, String year) {

		String url = StaticData.API_MAIN_URL;
		url += "/discover/tv";
		url += "?api_key=" + StaticData.API_KEY;
		url += "&language=" + StaticData.KOREAN;
		url += "&sort_by=popularity.desc";
		url += "&page=" + pageNumber;
		url += "&first_air_date_year=" + year;
		url += "&with_networks=213";

		return getMoviePreviewList(allService.getIdListByUrl(url));
	}

	// 연도별 MoviePreview 목록 반환 (File)
	public ArrayList<MoviePreview> getMoviesByYear(long lastId, String year) {

		ArrayList<Long> allMovieIdList = allService.getIdListByFile(StaticData.POPULAR_DESC_MOVIE_ID_LIST_FILE_PATH);

		// 매칭된 movie_id 만 담을 list 초기화
		ArrayList<Long> movieIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex;

		if (lastId == 0) {
			// 첫 번째 페이지면 0부터 시작
			startIndex = 0;
		} else {
			// 이전 페이지의 마지막 프로그램 다음 index 부터 검색
			startIndex = allMovieIdList.indexOf(lastId) + 1;
		}

		// 선택한 연도에 매칭되는 movie_id 리스트 생성 (최대 개수 = count)
		for (int i = startIndex; i < allMovieIdList.size(); i++) {

			JsonObject movie = allService.getMovieJsonById(allMovieIdList.get(i));

			try {
				// 해당 TV 프로그램의 첫 방영일의 연도가 year 값과 동일한지 검사
				if (year.equals(movie.get("first_air_date").getAsString().split("-")[0])) {
					movieIdList.add(allMovieIdList.get(i));
				}
			} catch (Exception e) {
				continue;
			}

			// 한 페이지에 띄울 영화의 개수를 충족하면 종료
			if (movieIdList.size() == count)
				break;
		}

		return getMoviePreviewList(movieIdList);
	}

	/* ------------ 페이지 데이터 개수 반환 ------------- */

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
	
	@Override
	public int getCountPage(int pageNumber, int condition) {

		return 0;
	}

	public int getCountPageByGenre(int pageNumber, int genreId) {
		
		String url = allService.searchMovieByGenreUrl(pageNumber, genreId);

		return getCount(url);
	}

	public int getCountPage(int pageNumber, String year) {
		
		return 0;		
	}

	/* ------ 공통 부분 -------- */

	// Json 데이터 MoviePreview 객체에 저장
	public MoviePreview getMoviePreviewById(long id) {

		MoviePreview moviePreview = new MoviePreview();

		ArrayList<Genre> genres = null;
		Genre genre = null;

		try {

			JsonObject mv = allService.getMovieJsonById(id);

			// movie_id
			moviePreview.setId(mv.get("id").getAsLong());

			// 포스터 URI
			try {
				moviePreview.setPosterPath(StaticData.API_IMAGE_URL + mv.get("poster_path").getAsString());
			} catch (Exception e) {
				moviePreview.setPosterPath(StaticData.EMPTY_IMAGE_URL);
			}

			// 제목
			moviePreview.setTitle(mv.get("title").getAsString());

			// 개봉일
			try {
				moviePreview.setReleaseDate(mv.get("release_date").getAsString());
			} catch (Exception e) {
				moviePreview.setReleaseDate(null);
			}

			// 영상 길이
			try {
				moviePreview.setRuntime(mv.get("runtime").getAsInt());
			} catch (Exception e) {
				moviePreview.setRuntime(0);
			}

			// 장르
			genres = new ArrayList<Genre>();

			for (JsonElement element : mv.get("genres").getAsJsonArray()) {
				genre = new Genre();
				genre.setId(element.getAsJsonObject().get("id").getAsInt());
				genre.setName(element.getAsJsonObject().get("name").getAsString());
				genres.add(genre);
			}

			moviePreview.setGenres(genres);

		} catch (Exception e) {
			System.out.println(id);
			e.printStackTrace();
		}

		return moviePreview;
	}

	// MoviePreview 객체 리스트 생성
	public ArrayList<MoviePreview> getMoviePreviewList(ArrayList<Long> movieIdList) {

		// 반환값을 담을 MoviePreview 리스트 선언
		ArrayList<MoviePreview> moviePreviewList = new ArrayList<MoviePreview>();

		for (long movieId : movieIdList) {
			moviePreviewList.add(getMoviePreviewById(movieId));
		}

		return moviePreviewList;
	}

	// Json 데이터 Movie 객체에 저장
	public Movie getMovieById(long id) {

		Movie movie = new Movie();

		ArrayList<Genre> genres = null;
		Genre genre = null;
		
		ArrayList<Cast> casts = null;
		Cast cast = null;

		try {

			JsonObject mv = allService.getMovieJsonWithCastsById(id);

			// movie_id
			movie.setId(mv.get("id").getAsLong());

			// poster URI
			try {
				movie.setPosterPath(StaticData.API_IMAGE_URL + mv.get("poster_path").getAsString());
			} catch (Exception e) {
				movie.setPosterPath(StaticData.EMPTY_IMAGE_URL);
			}
			
			// backdrop URI
			try {
				movie.setBackdropPath(StaticData.BACKGROUND_IMAGE_URL + mv.get("backdrop_path").getAsString());
			} catch (Exception e) {
				movie.setBackdropPath(StaticData.EMPTY_BACKGROUND_IMAGE_URL);
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

			// 출연 배우
			casts = new ArrayList<Cast>();
			
			for (JsonElement element : mv.get("casts").getAsJsonObject().get("cast").getAsJsonArray()) {
				
				JsonObject obj = element.getAsJsonObject();
				
				cast = new Cast();
				
				cast.setId(obj.get("id").getAsInt());
				
				try {
					cast.setProfilePath(StaticData.API_IMAGE_URL + obj.get("profile_path").getAsString());
				} catch (Exception e) {
					cast.setProfilePath(StaticData.EMPTY_IMAGE_URL);
				}
				
				cast.setName(obj.get("name").getAsString());
				cast.setCharacter(obj.get("character").getAsString());
				
				casts.add(cast);
			}
			
			movie.setCasts(casts);

			// 영화 개요
			movie.setOverview(mv.get("overview").getAsString());

			// 영상 스트리밍 URL
			movie.setHomepage(mv.get("homepage").getAsString());

			// 종영 여부 - Rumored, Planned, In Production, Post Production, Released, Canceled
			String status = mv.get("status").getAsString();
			
			switch (status) {
			case "Rumored":
				movie.setStatus("Rumored");
				break;
			case "Planned" :
				movie.setStatus("개봉 예정작");
				break;
			case "In Production" :
				movie.setStatus("촬영중");
				break;
			case "Post Production" :
				movie.setStatus("제작중");
				break;
			case "Released" :
				movie.setStatus("개봉작");
				break;	
			case "Canceled" :
				movie.setStatus("상영 취소작");
				break;
			}

		} catch (Exception e) {
			System.out.println(id);
			e.printStackTrace();
		}

		return movie;
	}

	// Movie 객체 리스트 생성
	public ArrayList<Movie> getMovieList(ArrayList<Long> movieIdList) {

		// 반환값을 담을 Movie 리스트 선언
		ArrayList<Movie> movies = new ArrayList<Movie>();

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

		return getMoviePreviewList(movieIdList);
	}

	

}
