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
		
		String url = allService.searchMovieByPopularDesc(pageNumber);

		return getMoviePreviewList(allService.getIdListByUrl(url));
	}

	// 인기순 - 오름차순 영화 목록 반환
	public ArrayList<MoviePreview> getPopularAscMovies(int pageNumber) {

		String url = allService.searchMovieByPopularAsc(pageNumber);

		return getMoviePreviewList(allService.getIdListByUrl(url));
	}

	/* ------ 개봉일순 검색 -------- */

	// 최신순 영화 목록 반환
	public ArrayList<MoviePreview> getLatestMovies(int pageNumber) {

		String url = allService.searchMovieLatest(pageNumber);

		return getMoviePreviewList(allService.getIdListByUrl(url));
	}

	// 오래된순 영화 목록 반환
	public ArrayList<MoviePreview> getOldestMovies(int pageNumber) {

		String url = allService.searchMovieOldest(pageNumber);

		return getMoviePreviewList(allService.getIdListByUrl(url));
	}

	/* ------ 장르별 검색 -------- */

	// 장르 id 목록에 매칭되는 MoviePreviewList 반환 (API)
	public ArrayList<MoviePreview> getMoviesByGenreIds(int pageNumber, int genreId) {

		String url = allService.searchMovieByGenreUrl(pageNumber, genreId);

		return getMoviePreviewList(allService.getIdListByUrl(url));
	}

	/* ------ 연도별 검색 -------- */

	// 연도별 MoviePreview 목록 반환 (API)
	public ArrayList<MoviePreview> getMoviesByYear(int pageNumber, String year) {

		String url = allService.searchMovieByYearUrl(pageNumber, year);

		return getMoviePreviewList(allService.getIdListByUrl(url));
	}

	/* ------------ 페이지 데이터 개수 반환 ------------- */

	// 인기순 / 날짜순 검색 총 컨텐츠 개수 반환 (API)
	@Override
	public int getCountPage(int pageNumber, int condition) {

		String url = null;

		switch (condition) {
		case 0:
			url = allService.searchMovieByPopularDesc(pageNumber);
			break;
		case 1:
			url = allService.searchMovieByPopularAsc(pageNumber);
			break;
		case 2:
			url = allService.searchMovieLatest(pageNumber);
			break;
		case 3:
			url = allService.searchMovieOldest(pageNumber);
			break;
		}

		return getCount(url);
	}

	// 표시될 Movie 의 총 개수 반환 - 장르별 검색
	public int getCountPageByGenre(int pageNumber, int genreId) {
		
		String url = allService.searchMovieByGenreUrl(pageNumber, genreId);

		return getCount(url);
	}

	// 표시될 Movie 의 총 개수 반환 - 연도별 검색
	public int getCountPage(int pageNumber, String year) {
		
		String url = allService.searchMovieByYearUrl(pageNumber, year);

		return getCount(url);		
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

}
