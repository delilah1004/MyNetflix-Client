package com.my.netflix.tv.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.netflix.aop.StaticData;
import com.my.netflix.model.TVProgram;
import com.my.netflix.test.api.AllService;

@Component
public class TvAPIService implements TvAPI {

	@Autowired
	AllService allService;

	// 한 페이지에 띄울 TV Program, 영화의 개수
	public static final int count = StaticData.count;

	// 넷플릭스에서 방영되는 모든 TV Program 목록 반환
	public ArrayList<TVProgram> getAllTVProgramsByPage(int pageNumber) {
		ArrayList<Long> allTvIdList = allService.getAllTVIds();

		// 매칭된 tvId 만 담을 list 초기화
		ArrayList<Long> tvIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		// for (long tvId : tvIdList) {
		for (int i = startIndex; i < startIndex + count; i++) {
			tvIdList.add(allTvIdList.get(i));
		}

		return allService.getTVProgramList(tvIdList);
	}

	// 넷플릭스에서 방영되는 모든 TV Program 의 개수 반환
	public int getCountPage() {

		ArrayList<Long> allTvIdList = allService.getAllTVIds();
		
		return allTvIdList.size();
	}
}
