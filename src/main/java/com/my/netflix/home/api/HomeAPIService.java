package com.my.netflix.home.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.netflix.all.api.AllService;
import com.my.netflix.aop.StaticData;
import com.my.netflix.home.model.MainContent;
import com.my.netflix.model.Movie;
import com.my.netflix.model.TVProgram;
import com.my.netflix.movie.api.MovieAPI;
import com.my.netflix.movie.service.MovieService;
import com.my.netflix.tv.api.TvFile;
import com.my.netflix.tv.service.TVService;

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
    
    
//    @Override
//	public ArrayList<MainContent> getMainContents() {
//
//        return allService.getMainContentList(getMainContentIds());
//    }

    // 인기 영화 Id 반환 (6페이지 순회) - 12개
    public ArrayList<Long> getMainContentIds() {

        ArrayList<Long> popularMovieIdList = allService.getIdListByFile(StaticData.HOME_POPULAR_DESC_MOVIE_ID_LIST_FILE_PATH);
        
        popularMovieIdList.subList(0, count);

        return popularMovieIdList;
    }
    
    
    
    /* ------    Movie     ------- */
    
    @Override
	public ArrayList<Movie> getBestPopularMovies() {

        return allService.getMovieList(getBestPopularMovieIds());
    }

    // 인기 영화 Id 반환 (6페이지 순회) - 12개
    public ArrayList<Long> getBestPopularMovieIds() {

        ArrayList<Long> popularMovieIdList = allService.getIdListByFile(StaticData.HOME_POPULAR_DESC_MOVIE_ID_LIST_FILE_PATH);
        
        popularMovieIdList.subList(0, count);

        return popularMovieIdList;
    }
    
    @Override
    public ArrayList<Movie> getNowPlayingMovies() {
    	
    	return allService.getMovieList(getNowPlayingMovieIds());
    }
    
    // 최신 영화 Id 반환 (6페이지 순회) - 12개
    public ArrayList<Long> getNowPlayingMovieIds() {
    	
        ArrayList<Long> nowPlayingMovieIdList = allService.getIdListByFile(StaticData.HOME_LATEST_MOVIE_ID_LIST_FILE_PATH);
        
        nowPlayingMovieIdList.subList(0, count);

        return nowPlayingMovieIdList;
    }
    
    
    /* ------    TV Program     ------- */
    

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
