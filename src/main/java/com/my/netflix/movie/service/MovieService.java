package com.my.netflix.movie.service;

import org.springframework.web.servlet.ModelAndView;

public interface MovieService {

	public void movieMain(ModelAndView mav);

	public void movieFullView(ModelAndView mav);
	
}
