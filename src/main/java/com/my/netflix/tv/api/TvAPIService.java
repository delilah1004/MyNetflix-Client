package com.my.netflix.tv.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

		ArrayList<Long> allTvIdList = allService.getTVIds(StaticData.TV_ID_LIST_FILE_PATH);

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

	/* ------ 장르별 검색 -------- */

	// 장르 id 목록에 매칭되는 TV Program 목록 반환
	public ArrayList<TVProgram> getTVProgramsByGenreIds(long lastId, ArrayList<Integer> genreIds) {

		ArrayList<Long> allTvIdList = allService.getTVIds(StaticData.TV_ID_LIST_FILE_PATH);

		// 매칭된 tvId 만 담을 list 초기화
		ArrayList<Long> tvIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex;

		if (lastId == 0) {
			// 첫 번째 페이지면 0부터 시작
			startIndex = 0;
		} else {
			// 이전 페이지의 마지막 프로그램 다음 index 부터 검색
			startIndex = allTvIdList.indexOf(lastId) + 1;
		}

		// 선택된 장르에 매칭되는 TV Program 의 id 리스트 생성 (최대 15개)
		for (int i = startIndex; i < allTvIdList.size(); i++) {

			JsonObject tv = allService.getTVJsonById(allTvIdList.get(i));

			// 해당 TV 프로그램의 장르 중 검색할 장르가 포함되어 있는지 검사
			for (JsonElement element : tv.get("genres").getAsJsonArray()) {
				if (genreIds.contains(element.getAsJsonObject().get("id").getAsInt())) {
					tvIdList.add(allTvIdList.get(i));
					break;
				}
			}

			// 한 페이지에 띄울 TV Program 의 개수를 충족하면 종료
			if (tvIdList.size() == count)
				break;
		}

		return allService.getTVProgramList(tvIdList);
	}

	/* ------ 연도별 검색 -------- */

	// 연도별 TV Program 목록 반환
	public ArrayList<TVProgram> getTVProgramsByYear(long lastId, String year) {

		ArrayList<Long> allTvIdList = allService.getTVIds(StaticData.TV_ID_LIST_FILE_PATH);

		// 매칭된 tvId 만 담을 list 초기화
		ArrayList<Long> tvIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex;

		if (lastId == 0) {
			// 첫 번째 페이지면 0부터 시작
			startIndex = 0;
		} else {
			// 이전 페이지의 마지막 프로그램 다음 index 부터 검색
			startIndex = allTvIdList.indexOf(lastId) + 1;
		}

		// 선택된 장르에 매칭되는 TV Program 의 id 리스트 생성 (최대 15개)
		for (int i = startIndex; i < allTvIdList.size(); i++) {

			JsonObject tv = allService.getTVJsonById(allTvIdList.get(i));

			try {
				// 해당 TV 프로그램의 첫 방영일의 연도가 year 값과 동일한지 검사
				if (year.equals(tv.get("first_air_date").getAsString().split("-")[0])) {
					tvIdList.add(allTvIdList.get(i));
				}
			} catch (Exception e) {
				continue;
			}

			// 한 페이지에 띄울 TV Program 의 개수를 충족하면 종료
			if (tvIdList.size() == count)
				break;
		}

		return allService.getTVProgramList(tvIdList);
	}

	/* ------ 인기순 검색 -------- */

	// 인기순 - 내림차순 TV Program 목록 반환
	public ArrayList<TVProgram> getPopularDescTVPrograms(int pageNumber) {

		ArrayList<Long> popularDescTvIdList = allService.getTVIds(StaticData.POPULAR_DESC_TV_ID_LIST_FILE_PATH);

		// 매칭된 tvId 만 담을 list 초기화
		ArrayList<Long> tvIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			tvIdList.add(popularDescTvIdList.get(i));
		}

		return allService.getTVProgramList(tvIdList);
	}

	// 인기순 - 오름차순 TV Program 목록 반환
	public ArrayList<TVProgram> getPopularAscTVPrograms(int pageNumber) {

		ArrayList<Long> popularAscTvIdList = allService.getTVIds(StaticData.POPULAR_ASC_TV_ID_LIST_FILE_PATH);

		// 매칭된 tvId 만 담을 list 초기화
		ArrayList<Long> tvIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			tvIdList.add(popularAscTvIdList.get(i));
		}

		return allService.getTVProgramList(tvIdList);
	}

	/* ------ 방영일순 검색 -------- */

	// 최신순 TV Program 목록 반환
	public ArrayList<TVProgram> getLatestTVPrograms(int pageNumber) {

		ArrayList<Long> latestTvIdList = allService.getTVIds(StaticData.LATEST_TV_ID_LIST_FILE_PATH);

		// 매칭된 tvId 만 담을 list 초기화
		ArrayList<Long> tvIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			tvIdList.add(latestTvIdList.get(i));
		}

		return allService.getTVProgramList(tvIdList);
	}

	// 오래된순 TV Program 목록 반환
	public ArrayList<TVProgram> getOldestTVPrograms(int pageNumber) {

		ArrayList<Long> oldestTvIdList = allService.getTVIds(StaticData.OLDEST_TV_ID_LIST_FILE_PATH);

		// 매칭된 tvId 만 담을 list 초기화
		ArrayList<Long> tvIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			tvIdList.add(oldestTvIdList.get(i));
		}

		return allService.getTVProgramList(tvIdList);
	}

	/* ------------------ 공통 부분 -------------------- */

	// 넷플릭스에서 방영되는 모든 TV Program 의 개수 반환
	public int getCountPage(int condition) {

		ArrayList<Long> tvIdList = new ArrayList<Long>();

		switch (condition) {
		case 0:
			tvIdList = allService.getTVIds(StaticData.POPULAR_DESC_TV_ID_LIST_FILE_PATH);
			break;
		case 1:
			tvIdList = allService.getTVIds(StaticData.POPULAR_ASC_TV_ID_LIST_FILE_PATH);
			break;
		case 2:
			tvIdList = allService.getTVIds(StaticData.LATEST_TV_ID_LIST_FILE_PATH);
			break;
		case 3:
			tvIdList = allService.getTVIds(StaticData.OLDEST_TV_ID_LIST_FILE_PATH);
			break;
		}

		return tvIdList.size();
	}

	// id로 TV Program 객체 반환
	public TVProgram getTVProgramById(long id) {

		return allService.getTVById(id);
	}

}
