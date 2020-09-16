package com.my.netflix.tv.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.my.netflix.aop.StaticData;
import com.my.netflix.model.TVProgram;
import com.my.netflix.test.api.GenreService;
import com.my.netflix.tv.api.TvAPI;

@Component
public class TVServiceImp implements TVService {

	@Autowired
	TvAPI tvAPI;
	@Autowired
	GenreService genreService;

	@Override
	public void tvMain(ModelAndView mav) {

		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int pageNumber = 1;

		if (request.getParameter("pageNumber") != null)
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

		ArrayList<TVProgram> array = tvAPI.getAllTVProgramsByPage(pageNumber);

		int condition = 0;

		int tvListCount = tvAPI.getCountPage(condition);

		int listSize = StaticData.count;

		String check = "TV 프로그램 메인페이지입니다.";

		mav.addObject("check", check);
		mav.addObject("array", array);
		mav.addObject("tvListCount", tvListCount);
		mav.addObject("listSize", listSize);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("condition", condition);

		mav.setViewName("tv/main.hm");
	}

	@Override
	public void setView(ModelAndView mav) {

		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int pageNumber = 1;
		int condition = 0;

		if (request.getParameter("pageNumber") != null)
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		if (request.getParameter("condition") != null)
			condition = Integer.parseInt(request.getParameter("condition"));

		ArrayList<TVProgram> array = new ArrayList<TVProgram>();

		switch (condition) {
		case 0:
			array = tvAPI.getPopularDescTVPrograms(pageNumber);
			break;
		case 1:
			array = tvAPI.getPopularAscTVPrograms(pageNumber);
			break;
		case 2:
			array = tvAPI.getLatestTVPrograms(pageNumber);
			break;
		case 3:
			array = tvAPI.getOldestTVPrograms(pageNumber);
			break;
		case 4:

			if (request.getParameter("selectedGenres") != null) {
				System.out.println(request.getParameter("selectedGenres"));
			}

			/*
			 * String
			 * 
			 * ArrayList<Integer> genreIds = new ArrayList<Integer>();
			 * 
			 * for() { genreIds } array = tvAPI.getTVProgramsByGenreIds(lastId, genreIds);
			 * break;
			 */
		}

		int tvListCount = tvAPI.getCountPage(condition);
		int listSize = StaticData.count;

		String check = "TV 프로그램 메인페이지입니다.";

		mav.addObject("check", check);

		mav.addObject("array", array);
		mav.addObject("tvListCount", tvListCount);
		mav.addObject("listSize", listSize);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("condition", condition);

		ArrayList<String> genres = genreService.getTVGenreNames();

		mav.addObject("genres", genres);

		mav.setViewName("tv/main.hm");
	}

	@Override
	public void genreView(ModelAndView mav) {

		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int pageNumber = 1;
		int condition = 0;
		long lastId = 0;

		if (request.getParameter("pageNumber") != null)
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		if (request.getParameter("condition") != null)
			condition = Integer.parseInt(request.getParameter("condition"));
		if (request.getParameter("lastId") != null)
			lastId = Long.parseLong(request.getParameter("lastId"));

		ArrayList<TVProgram> array = new ArrayList<TVProgram>();

		switch (condition) {
		case 0:
			
			array = tvAPI.getPopularDescTVPrograms(pageNumber);
			break;
			
		case 1:
			
			array = tvAPI.getPopularAscTVPrograms(pageNumber);
			break;

		case 2:

			array = tvAPI.getLatestTVPrograms(pageNumber);
			break;

		case 3:

			array = tvAPI.getOldestTVPrograms(pageNumber);
			break;

		case 4:
			
			String selectedGenres = null;

			if (request.getParameter("selectedGenres") != null) {
				selectedGenres = request.getParameter("selectedGenres");
			}

			String[] genreNames = selectedGenres.split(",");

			ArrayList<Integer> genreIds = new ArrayList<Integer>();

			for (String genreName : genreNames) {
				genreIds.add(genreService.getTVGenreId(genreName));
			}

			array = tvAPI.getTVProgramsByGenreIds(lastId, genreIds);
			break;
		}

		int tvListCount = tvAPI.getCountPage(condition);
		int listSize = StaticData.count;

		String check = "TV 프로그램 메인페이지입니다.";

		mav.addObject("check", check);

		mav.addObject("array", array);
		mav.addObject("tvListCount", tvListCount);
		mav.addObject("listSize", listSize);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("condition", condition);

		ArrayList<String> genres = genreService.getTVGenreNames();

		mav.addObject("genres", genres);

		mav.setViewName("tv/main.hm");
	}
	
	@Override
	public void yearView(ModelAndView mav) {

		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int pageNumber = 1;
		int condition = 0;
		long lastId = 0;

		if (request.getParameter("pageNumber") != null)
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		if (request.getParameter("condition") != null)
			condition = Integer.parseInt(request.getParameter("condition"));
		if (request.getParameter("lastId") != null)
			lastId = Long.parseLong(request.getParameter("lastId"));

		ArrayList<TVProgram> array = new ArrayList<TVProgram>();

		switch (condition) {
		case 0:
			
			array = tvAPI.getPopularDescTVPrograms(pageNumber);
			break;
			
		case 1:
			
			array = tvAPI.getPopularAscTVPrograms(pageNumber);
			break;

		case 2:

			array = tvAPI.getLatestTVPrograms(pageNumber);
			break;

		case 3:

			array = tvAPI.getOldestTVPrograms(pageNumber);
			break;

		case 4:
			
			String selectedGenres = null;

			if (request.getParameter("selectedGenres") != null) {
				selectedGenres = request.getParameter("selectedGenres");
			}

			String[] genreNames = selectedGenres.split(",");

			ArrayList<Integer> genreIds = new ArrayList<Integer>();

			for (String genreName : genreNames) {
				genreIds.add(genreService.getTVGenreId(genreName));
			}

			array = tvAPI.getTVProgramsByGenreIds(lastId, genreIds);
			break;
			
		case 5:
			
			String year = request.getParameter("year");

			array = tvAPI.getTVProgramsByYear(lastId, year);
			break;
			
		}

		int tvListCount = tvAPI.getCountPage(condition);
		int listSize = StaticData.count;

		String check = "TV 프로그램 메인페이지입니다.";

		mav.addObject("check", check);

		mav.addObject("array", array);
		mav.addObject("tvListCount", tvListCount);
		mav.addObject("listSize", listSize);
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

		TVProgram tv = tvAPI.getTVProgramById(tvId);

		mav.addObject("check", check);
		mav.addObject("tv", tv);

		mav.setViewName("tv/fullView.hm");
	}

}
