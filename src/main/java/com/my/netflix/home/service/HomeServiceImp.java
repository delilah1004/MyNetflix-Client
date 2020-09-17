package com.my.netflix.home.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.my.netflix.home.api.HomeAPI;
import com.my.netflix.home.model.MainContent;
import com.my.netflix.home.model.MainMovie;
import com.my.netflix.home.model.MainTVProgram;

@Component
public class HomeServiceImp implements HomeService {

	@Autowired
	HomeAPI homeAPI;

	@Override
	public void main(ModelAndView mav) {

		String check = "Home 메인페이지입니다.";

		mav.addObject("check", check);

		// 대표 슬라이드
		ArrayList<MainContent> mainContents = homeAPI.getMainContents();
		mav.addObject("mainContents", mainContents);

		// 인기 영화 슬라이드
		ArrayList<MainMovie> popularMovies = homeAPI.getBestPopularMovies();
		mav.addObject("popularMovies", popularMovies);

		// 최신 영화 슬라이드
		ArrayList<MainMovie> nowPlayingMovies = homeAPI.getNowPlayingMovies();
		mav.addObject("nowPlayingMovies", nowPlayingMovies);

		// 인기 TV 프로그램 슬라이드
		ArrayList<MainTVProgram> popularTVs = homeAPI.getBestPopularTVPrograms();
		mav.addObject("popularTVs", popularTVs);

		// 최신 TV 프로그램 슬라이드
		ArrayList<MainTVProgram> onTheAirTVs = homeAPI.getOnTheAirTVPrograms();
		mav.addObject("onTheAirTVs", onTheAirTVs);

		mav.setViewName("home.hm");
	}

}
