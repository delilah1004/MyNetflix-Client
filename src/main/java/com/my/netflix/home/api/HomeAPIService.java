package com.my.netflix.home.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.my.netflix.all.api.AllService;
import com.my.netflix.aop.StaticData;
import com.my.netflix.home.model.MainContent;
import com.my.netflix.home.model.MainMovie;
import com.my.netflix.home.model.MainTVProgram;

@Component
public class HomeAPIService implements HomeAPI {

	@Autowired
	AllService allService;

	// 콘텐츠 개수
	private static final int count = 12;

	/* ------ Main Content ------- */

	@Override
	public ArrayList<MainContent> getMainContents() {

		ArrayList<MainContent> mainContents = new ArrayList<MainContent>();
		MainContent mainContent = new MainContent();

		ArrayList<Long> movieIdList = allService.getIdListByFile(StaticData.HOME_MAIN_MOVIE_ID_LIST_FILE_PATH);

		for (long movieId : movieIdList) {
			mainContent = getMainMovieContentById(movieId);
			mainContents.add(mainContent);
		}

		ArrayList<Long> tvIdList = allService.getIdListByFile(StaticData.HOME_MAIN_TV_ID_LIST_FILE_PATH);

		for (long tvId : tvIdList) {
			mainContent = getMainTVContentById(tvId);
			mainContents.add(mainContent);
		}

		return mainContents;
	}

	// Json 데이터 MainContent 객체에 저장 - Movie
	public MainContent getMainMovieContentById(long id) {

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

	// Json 데이터 MainContent 객체에 저장 - TV Program
	public MainContent getMainTVContentById(long id) {

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

	/* ------ Movie ------- */

	// 인기 영화 Id 반환 (6페이지 순회) - 12개
	@Override
	public ArrayList<MainMovie> getBestPopularMovies() {

		ArrayList<Long> popularMovieIdList = allService
				.getIdListByFile(StaticData.HOME_POPULAR_DESC_MOVIE_ID_LIST_FILE_PATH);

		popularMovieIdList.subList(0, count);

		return getMainMovieList(popularMovieIdList);
	}

	// 최신 영화 Id 반환 (6페이지 순회) - 12개
	@Override
	public ArrayList<MainMovie> getNowPlayingMovies() {

		ArrayList<Long> nowPlayingMovieIdList = allService
				.getIdListByFile(StaticData.HOME_LATEST_MOVIE_ID_LIST_FILE_PATH);

		nowPlayingMovieIdList.subList(0, count);

		return getMainMovieList(nowPlayingMovieIdList);
	}

	// Json 데이터 MainMovie 객체에 저장
	public MainMovie getMainMovieById(long id) {

		MainMovie mainMovie = new MainMovie();

		try {

			JsonObject mv = allService.getMovieJsonById(id);

			// movie_id
			mainMovie.setId(mv.get("id").getAsLong());

			// poster Path
			try {
				mainMovie.setPosterPath(StaticData.API_IMAGE_URL + mv.get("poster_path").getAsString());
			} catch (Exception e) {
				mainMovie.setPosterPath(StaticData.EMPTY_BACKGROUND_IMAGE_URL);
			}

		} catch (Exception e) {
			System.out.println(id);
			e.printStackTrace();
		}

		return mainMovie;
	}

	// MainMovie 객체 리스트 생성
	public ArrayList<MainMovie> getMainMovieList(ArrayList<Long> movieIdList) {

		ArrayList<MainMovie> mainMovieList = new ArrayList<MainMovie>();

		for (long movieId : movieIdList) {
			mainMovieList.add(getMainMovieById(movieId));
		}

		return mainMovieList;
	}

	/* ------ TV Program ------- */

	// 인기 TV 프로그램 Id 반환 (6페이지 순회) - 12개
	@Override
	public ArrayList<MainTVProgram> getBestPopularTVPrograms() {

		ArrayList<Long> popularTvIdList = allService.getIdListByFile(StaticData.HOME_POPULAR_DESC_TV_ID_LIST_FILE_PATH);

		popularTvIdList.subList(0, count);

		return getMainTVProgramList(popularTvIdList);
	}

	// 최신 TV 프로그램 Id 반환 (6페이지 순회) - 12개
	@Override
	public ArrayList<MainTVProgram> getOnTheAirTVPrograms() {

		ArrayList<Long> onTheAirTvIdList = allService.getIdListByFile(StaticData.HOME_LATEST_TV_ID_LIST_FILE_PATH);

		onTheAirTvIdList.subList(0, count);

		return getMainTVProgramList(onTheAirTvIdList);
	}

	// Json 데이터 MainTVProgram 객체에 저장
	public MainTVProgram getMainTVProgramById(long id) {

		MainTVProgram mainTVProgram = new MainTVProgram();

		try {

			JsonObject tv = allService.getTVJsonById(id);

			// movie_id
			mainTVProgram.setId(tv.get("id").getAsLong());

			// poster Path
			try {
				mainTVProgram.setPosterPath(StaticData.API_IMAGE_URL + tv.get("poster_path").getAsString());
			} catch (Exception e) {
				mainTVProgram.setPosterPath(StaticData.EMPTY_BACKGROUND_IMAGE_URL);
			}

		} catch (Exception e) {
			System.out.println(id);
			e.printStackTrace();
		}

		return mainTVProgram;
	}

	// MainTVProgram 객체 리스트 생성
	public ArrayList<MainTVProgram> getMainTVProgramList(ArrayList<Long> tvIdList) {

		ArrayList<MainTVProgram> mainTVProgramList = new ArrayList<MainTVProgram>();

		for (long tvId : tvIdList) {
			mainTVProgramList.add(getMainTVProgramById(tvId));
		}

		return mainTVProgramList;
	}

}
