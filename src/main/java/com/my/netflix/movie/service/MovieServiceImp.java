package com.my.netflix.movie.service;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MovieServiceImp implements MovieService {

	@Override
	public void main(ModelAndView mav) {
		
		String check = "영화 메인페이지입니다.";

		mav.addObject("check", check);
		mav.setViewName("movie/main.hm");
	}

	@Override
	public void fullView(ModelAndView mav) {
		
		String check = "영화 상세페이지입니다.";

		mav.addObject("check", check);
		mav.setViewName("movie/fullView.hm");
	}
}
