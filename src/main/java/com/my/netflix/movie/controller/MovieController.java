package com.my.netflix.movie.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.netflix.movie.service.MovieService;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;

	@RequestMapping(value = "/movie/main.mn", method = RequestMethod.GET)
	public ModelAndView movieMain(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("movie/main.mn");

		ModelAndView mav = new ModelAndView();
		
		mav.addObject("request", request);
		
		movieService.main(mav);

		return mav;
	}
	
	@RequestMapping(value = "/movie/fullView.mn", method = RequestMethod.GET)
	public ModelAndView movieFullView(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("movie/fullView.mn");

		ModelAndView mav = new ModelAndView();
		movieService.fullView(mav);

		return mav;
	}
}