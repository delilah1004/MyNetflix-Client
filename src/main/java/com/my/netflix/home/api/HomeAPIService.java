package com.my.netflix.home.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.my.netflix.all.api.AllService;
import com.my.netflix.all.model.Movie;
import com.my.netflix.all.model.TVProgram;
import com.my.netflix.aop.StaticData;
import com.my.netflix.home.model.MainContent;
import com.my.netflix.movie.api.MovieAPI;
import com.my.netflix.tv.api.TvFile;

@Component
public class HomeAPIService implements HomeAPI {

	@Autowired
	AllService allService;
	@Autowired
	TvFile tvAPI;
	@Autowired
	MovieAPI movieAPI;

	// 콘텐츠 개수
	private static final int count = 12;

	/* ------ Main Content ------- */

	public MainContent getMovieById(long id) {

		MainContent mainContent = new MainContent();

		try {

			JsonObject mv = allService.getMovieJsonById(id);

			// movie_id
			mainContent.setId(mv.get("id").getAsLong());

			// 제목
			mainContent.setName(mv.get("title").getAsString());

			// backdrop Path
			try {
				mainContent.setBackdropPath(StaticData.BACKGROUND_IMAGE_URL + mv.get("backdrop_path").getAsString());
			} catch (Exception e) {
				mainContent.setBackdropPath(StaticData.EMPTY_BACKGROUND_IMAGE_URL);
			}
			
			// homepage
			mainContent.setHomepage(mv.get("homepage").getAsString());

		} catch (Exception e) {
			System.out.println(id);
			e.printStackTrace();
		}

		return mainContent;
	}

	public MainContent getTvById(long id) {

		MainContent mainContent = new MainContent();

		try {

			JsonObject tv = allService.getTVJsonById(id);

			// tv_id
			mainContent.setId(tv.get("id").getAsLong());

			// 제목
			mainContent.setName(tv.get("name").getAsString());

			// backdrop Path
			try {
				mainContent.setBackdropPath(StaticData.BACKGROUND_IMAGE_URL + tv.get("backdrop_path").getAsString());
			} catch (Exception e) {
				mainContent.setBackdropPath(StaticData.EMPTY_BACKGROUND_IMAGE_URL);
			}

			// homepage
			mainContent.setHomepage(tv.get("homepage").getAsString());

		} catch (Exception e) {
			System.out.println(id);
			e.printStackTrace();
		}

		return mainContent;
	}

	@Override
	public ArrayList<MainContent> getMainContents() {

		ArrayList<MainContent> mainContents = new ArrayList<MainContent>();
		MainContent mainContent = new MainContent();

		ArrayList<Long> movieIdList = allService.getIdListByFile(StaticData.HOME_MAIN_MOVIE_ID_LIST_FILE_PATH);

		for (long movieId : movieIdList) {
			mainContent = getMovieById(movieId);
			mainContents.add(mainContent);
		}

		ArrayList<Long> tvIdList = allService.getIdListByFile(StaticData.HOME_MAIN_TV_ID_LIST_FILE_PATH);

		for (long tvId : tvIdList) {
			mainContent = getTvById(tvId);
			mainContents.add(mainContent);
		}

		return mainContents;
	}

	/* ------ Movie ------- */

	@Override
	public ArrayList<Movie> getBestPopularMovies() {

		return movieAPI.getMovieList(getBestPopularMovieIds());
	}

	// 인기 영화 Id 반환 (6페이지 순회) - 12개
	public ArrayList<Long> getBestPopularMovieIds() {

		ArrayList<Long> popularMovieIdList = allService
				.getIdListByFile(StaticData.HOME_POPULAR_DESC_MOVIE_ID_LIST_FILE_PATH);

		popularMovieIdList.subList(0, count);

		return popularMovieIdList;
	}

	@Override
	public ArrayList<Movie> getNowPlayingMovies() {

		return movieAPI.getMovieList(getNowPlayingMovieIds());
	}

	// 최신 영화 Id 반환 (6페이지 순회) - 12개
	public ArrayList<Long> getNowPlayingMovieIds() {

		ArrayList<Long> nowPlayingMovieIdList = allService
				.getIdListByFile(StaticData.HOME_LATEST_MOVIE_ID_LIST_FILE_PATH);

		nowPlayingMovieIdList.subList(0, count);

		return nowPlayingMovieIdList;
	}

	/* ------ TV Program ------- */

	@Override
	public ArrayList<TVProgram> getBestPopularTVPrograms() {

		return tvAPI.getTVProgramList(getBestPopularTVIds());
	}

	// 인기 TV 프로그램 Id 반환 (6페이지 순회) - 12개
	public ArrayList<Long> getBestPopularTVIds() {

		ArrayList<Long> popularTvIdList = allService.getIdListByFile(StaticData.HOME_POPULAR_DESC_TV_ID_LIST_FILE_PATH);

		popularTvIdList.subList(0, count);

		return popularTvIdList;
	}

	@Override
	public ArrayList<TVProgram> getOnTheAirTVPrograms() {

		return tvAPI.getTVProgramList(getOnTheAirTVIds());
	}

	// 최신 TV 프로그램 Id 반환 (6페이지 순회) - 12개
	public ArrayList<Long> getOnTheAirTVIds() {

		ArrayList<Long> onTheAirTvIdList = allService.getIdListByFile(StaticData.HOME_LATEST_TV_ID_LIST_FILE_PATH);

		onTheAirTvIdList.subList(0, count);

		return onTheAirTvIdList;
	}

}
