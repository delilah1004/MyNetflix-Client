package com.my.netflix.tv.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.my.netflix.all.api.GenreService;
import com.my.netflix.aop.StaticData;
import com.my.netflix.tv.api.TvJsonArray;
import com.my.netflix.tv.model.TVProgramPreview;

@Component
public class TVServiceImp implements TVService {
	
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

		// 페이지 번호, 검색 분류, 총 데이터 개수 초기화
		int pageNumber = 1;
		int condition = 0;
		int tvTotalCount = 0;

		// 페이지 번호가 존재하면 갱신
		if (request.getParameter("pageNumber") != null)
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		// 검색 분류값이 존재하면 갱신
		if (request.getParameter("condition") != null)
			condition = Integer.parseInt(request.getParameter("condition"));

		ArrayList<TVProgramPreview> array = new ArrayList<TVProgramPreview>();

		switch (condition) {
		
		// 인기 내림차순
		case 0:
			
			array = tvJsonArray.getPopularDescTVPrograms(pageNumber);
			tvTotalCount = tvJsonArray.getCountPage(pageNumber, condition);
			break;
		
		// 인기 오름차순
		case 1:
			
			array = tvJsonArray.getPopularAscTVPrograms(pageNumber);
			tvTotalCount = tvJsonArray.getCountPage(pageNumber, condition);
			break;
			
		// 최신순
		case 2:
			
			array = tvJsonArray.getLatestTVPrograms(pageNumber);
			tvTotalCount = tvJsonArray.getCountPage(pageNumber, condition);
			break;
		
		// 오래된순
		case 3:
			
			array = tvJsonArray.getOldestTVPrograms(pageNumber);
			tvTotalCount = tvJsonArray.getCountPage(pageNumber, condition);
			break;
			
		// 장르별 검색
		case 4:
			
			ArrayList<Integer> genreIds = new ArrayList<Integer>();

			// 장르명 리스트 장르 Id 리스트로 변경
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
		
		// 연도별 검색
		case 5:

			String year = request.getParameter("year");

			array = tvJsonArray.getTVProgramsByYear(pageNumber, year);
			tvTotalCount = tvJsonArray.getCountPage(pageNumber, year);
			break;
			
		}

		// 화면에 뿌려줄 TVProgramPreview 객체 리스트
		mav.addObject("array", array);
		
		// TVProgramPreview 객체의 총 개수
		mav.addObject("tvTotalCount", tvTotalCount);

		// 한 페이지에 뿌려줄 TVProgramPreview 객체의 개수
		mav.addObject("tvBlockCount", StaticData.count);

		// 현재 페이지 번호 (기본값 : 1)
		mav.addObject("pageNumber", pageNumber);
		
		// 검색 분류 인덱스 (인기 내림차순 : 0 / 인기 오름차순 : 1 / 상영일 내림차순 : 2 / 상영일 오름차순 : 3 / 장르별 검색 : 4 / 연도별 검색 : 5)
		mav.addObject("condition", condition);

		ArrayList<String> genres = genreService.getTVGenreNames();

		// 장르별 검색시 뿌려줄 모든 장르의 데이터
		mav.addObject("genres", genres);

		mav.setViewName("tv/main.hm");
	}


	@Override
	public void tvFullView(ModelAndView mav) {
		
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String check = "TV 프로그램 상세페이지입니다.";
		
		mav.addObject("check", check);

		long tvId = Long.parseLong(request.getParameter("tvId"));

		TVProgramPreview tv = tvJsonArray.getTVPreviewById(tvId);
		
		mav.addObject("tv", tv);

		mav.setViewName("tv/fullView.hm");
	}

}
