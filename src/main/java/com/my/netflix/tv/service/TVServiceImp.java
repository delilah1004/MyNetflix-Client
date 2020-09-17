package com.my.netflix.tv.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.my.netflix.all.api.GenreService;
import com.my.netflix.all.model.TVProgram;
import com.my.netflix.aop.StaticData;
import com.my.netflix.tv.api.TvFile;
import com.my.netflix.tv.api.TvJsonArray;

@Component
public class TVServiceImp implements TVService {

	@Autowired
	TvFile tvFile;
	@Autowired
	TvJsonArray tvJsonArray;
	@Autowired
	GenreService genreService;

	@Override
	public void setView(ModelAndView mav) {

		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String check = "TV 프로그램 메인페이지입니다.";

		mav.addObject("check", check);

		int pageNumber = 1;
		int condition = 0;
		int tvTotalCount = 0;

		if (request.getParameter("pageNumber") != null)
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		if (request.getParameter("condition") != null)
			condition = Integer.parseInt(request.getParameter("condition"));

		ArrayList<TVProgram> array = new ArrayList<TVProgram>();

		switch (condition) {
		
		case 0:
			
			array = tvFile.getPopularDescTVPrograms(pageNumber);
			tvTotalCount = tvFile.getCountPage(condition);
			break;
			
		case 1:
			
			array = tvFile.getPopularAscTVPrograms(pageNumber);
			tvTotalCount = tvFile.getCountPage(condition);
			break;
			
		case 2:
			
			array = tvFile.getLatestTVPrograms(pageNumber);
			tvTotalCount = tvFile.getCountPage(condition);
			break;
			
		case 3:
			
			array = tvFile.getOldestTVPrograms(pageNumber);
			tvTotalCount = tvFile.getCountPage(condition);
			break;
			
		case 4:
			
			ArrayList<Integer> genreIds = new ArrayList<Integer>();

			if (request.getParameter("selectedGenres") != null) {
				String selectedGenres = request.getParameter("selectedGenres");
				
				if(selectedGenres.contains(",")) {
					String[] genreNames = selectedGenres.split(",");
					for (String genreName : genreNames) {
						genreIds.add(genreService.getTVGenreId(genreName));
					}
				} else {
					genreIds.add(genreService.getTVGenreId(selectedGenres));
				}
			}

			array = tvJsonArray.getTVProgramsByGenreIds(pageNumber, genreIds);
			tvTotalCount = tvJsonArray.getCountPage(pageNumber, genreIds);

			break;
			
		case 5:

			String year = request.getParameter("year");

			array = tvJsonArray.getTVProgramsByYear(pageNumber, year);
			tvTotalCount = tvJsonArray.getCountPage(pageNumber, year);
			break;
			
		}

		mav.addObject("array", array);
		mav.addObject("tvListCount", tvTotalCount);

		int pageCount = StaticData.count;
		mav.addObject("listSize", pageCount);

		mav.addObject("pageNumber", pageNumber);
		mav.addObject("condition", condition);

		ArrayList<String> genres = genreService.getTVGenreNames();

		mav.addObject("genres", genres);

		mav.setViewName("tv/main.hm");
	}


	@Override
	public void tvFullView(ModelAndView mav) {

		String check = "TV 프로그램 상세페이지입니다.";

		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		long tvId = Long.parseLong(request.getParameter("tvId"));

		TVProgram tv = tvFile.getTVById(tvId);

		mav.addObject("check", check);
		mav.addObject("tv", tv);

		mav.setViewName("tv/fullView.hm");
	}

}
