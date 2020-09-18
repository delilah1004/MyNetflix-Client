package com.my.netflix.test;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.netflix.all.api.AllService;
import com.my.netflix.aop.StaticData;
import com.my.netflix.tv.model.TVProgramPreview;

@Component
public class TvFileService implements TvFile {

	@Autowired
	AllService allService;

	// 한 페이지에 띄울 TV Program, 영화의 개수
	public static final int count = StaticData.count;


	/* ------ 인기순 검색 -------- */

	// 인기순 - 내림차순 TV Program 목록 반환
	public ArrayList<TVProgramPreview> getPopularDescTVPrograms(int pageNumber) {

		ArrayList<Long> popularDescTvIdList = allService.getIdListByFile(StaticData.POPULAR_DESC_TV_ID_LIST_FILE_PATH);

		// 매칭된 tvId 만 담을 list 초기화
		ArrayList<Long> tvIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			tvIdList.add(popularDescTvIdList.get(i));
		}

		return getTVProgramList(tvIdList);
	}

	// 인기순 - 오름차순 TV Program 목록 반환
	public ArrayList<TVProgramPreview> getPopularAscTVPrograms(int pageNumber) {

		ArrayList<Long> popularAscTvIdList = allService.getIdListByFile(StaticData.POPULAR_ASC_TV_ID_LIST_FILE_PATH);

		// 매칭된 tvId 만 담을 list 초기화
		ArrayList<Long> tvIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			tvIdList.add(popularAscTvIdList.get(i));
		}

		return getTVProgramList(tvIdList);
	}

	/* ------ 방영일순 검색 -------- */

	// 최신순 TV Program 목록 반환
	public ArrayList<TVProgramPreview> getLatestTVPrograms(int pageNumber) {

		ArrayList<Long> latestTvIdList = allService.getIdListByFile(StaticData.LATEST_TV_ID_LIST_FILE_PATH);

		// 매칭된 tvId 만 담을 list 초기화
		ArrayList<Long> tvIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			tvIdList.add(latestTvIdList.get(i));
		}

		return getTVProgramList(tvIdList);
	}

	// 오래된순 TV Program 목록 반환
	public ArrayList<TVProgramPreview> getOldestTVPrograms(int pageNumber) {

		ArrayList<Long> oldestTvIdList = allService.getIdListByFile(StaticData.OLDEST_TV_ID_LIST_FILE_PATH);

		// 매칭된 tvId 만 담을 list 초기화
		ArrayList<Long> tvIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		for (int i = startIndex; i < startIndex + count; i++) {
			tvIdList.add(oldestTvIdList.get(i));
		}

		return getTVProgramList(tvIdList);
	}
	
	/* ------ 장르별 검색 -------- */
	

	// 장르 id 목록에 매칭되는 TV Program 목록 반환
	public ArrayList<TVProgramPreview> getTVProgramsByGenreIds(long lastId, ArrayList<Integer> genreIds) {

		ArrayList<Long> allTvIdList = allService.getIdListByFile(StaticData.TV_ID_LIST_FILE_PATH);

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

		// 선택된 장르에 매칭되는 TV Program 의 id 리스트 생성 (최대 개수 = count)
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

		return getTVProgramList(tvIdList);
	}

	/* ------ 연도별 검색 -------- */

	// 연도별 TV Program 목록 반환
	public ArrayList<TVProgramPreview> getTVProgramsByYear(long lastId, String year) {

		ArrayList<Long> allTvIdList = allService.getIdListByFile(StaticData.TV_ID_LIST_FILE_PATH);

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

		// 연도에 매칭되는 TV Program 의 id 리스트 생성 (최대 개수 = count)
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

		return getTVProgramList(tvIdList);
	}


	/* --------------- 페이지 데이터 개수 반환 ------------------ */

	// 넷플릭스에서 방영되는 모든 TV Program 의 개수 반환
	public int getCountPage(int condition) {

		int count = 1;

		ArrayList<Long> tvIdList = new ArrayList<Long>();

		switch (condition) {
		case 0:
			tvIdList = allService.getIdListByFile(StaticData.POPULAR_DESC_TV_ID_LIST_FILE_PATH);
			count = tvIdList.size();
			break;
		case 1:
			tvIdList = allService.getIdListByFile(StaticData.POPULAR_ASC_TV_ID_LIST_FILE_PATH);
			count = tvIdList.size();
			break;
		case 2:
			tvIdList = allService.getIdListByFile(StaticData.LATEST_TV_ID_LIST_FILE_PATH);
			count = tvIdList.size();
			break;
		case 3:
			tvIdList = allService.getIdListByFile(StaticData.OLDEST_TV_ID_LIST_FILE_PATH);
			count = tvIdList.size();
			break;
		}

		return count;
	}
	
	/* ------------------ 공통 부분 -------------------- */	
	
	// id 로 TV 프로그램의 모든 정보 TVProgram 객체로 반환
    public TVProgramPreview getTVById(long id) {

        // 반환값을 담을 TVProgram 객체 선언
        TVProgramPreview tvProgram = new TVProgramPreview();
        // season 정보와 genre 정보를 담을 리스트 선언
		/*
		 * ArrayList<Integer> seasons, genreIds; ArrayList<String> genreNames;
		 */

        try {

            JsonObject tv = allService.getTVJsonById(id);

            // tv_id
            tvProgram.setId(tv.get("id").getAsLong());

            // 제목
            tvProgram.setName(tv.get("name").getAsString());
			/*
			 * // 영상 길이 try {
			 * tvProgram.setEpisodeRunTime(tv.get("episode_run_time").getAsInt()); } catch
			 * (Exception e) { tvProgram.setEpisodeRunTime(0); }
			 * 
			 * // 장르 genreIds = new ArrayList<Integer>(); genreNames = new
			 * ArrayList<String>();
			 * 
			 * for (JsonElement element : tv.get("genres").getAsJsonArray()) {
			 * genreIds.add(element.getAsJsonObject().get("id").getAsInt());
			 * genreNames.add(element.getAsJsonObject().get("name").getAsString()); }
			 * 
			 * tvProgram.setGenreIds(genreIds); tvProgram.setGenreNames(genreNames);
			 * 
			 * // 개요 tvProgram.setOverview(tv.get("overview").getAsString());
			 */

            // 포스터 URI
            try {
                tvProgram.setPosterPath(StaticData.API_IMAGE_URL + tv.get("poster_path").getAsString());
            } catch (Exception e) {
                tvProgram.setPosterPath(StaticData.EMPTY_IMAGE_URL);
            }
			/*
			 * // 영상 스트리밍 URL tvProgram.setHomepage(tv.get("homepage").getAsString());
			 */

            // 방영일 정보
            try {
                tvProgram.setFirstAirDate(tv.get("first_air_date").getAsString());
            } catch (Exception e) {
                tvProgram.setFirstAirDate(null);
            }

            try {
                tvProgram.setLastAirDate(tv.get("last_air_date").getAsString());
            } catch (Exception e) {
                tvProgram.setLastAirDate(null);
            }
			/*
			 * // 인기도 tvProgram.setPopularity(tv.get("popularity").getAsDouble());
			 * 
			 * // 시즌 정보 seasons = new ArrayList<Integer>();
			 * 
			 * for (JsonElement element : tv.get("seasons").getAsJsonArray()) {
			 * seasons.add(element.getAsJsonObject().get("season_number").getAsInt()); }
			 * 
			 * tvProgram.setSeasons(seasons);
			 * 
			 * // 종영 여부 tvProgram.setStatus(tv.get("status").getAsString());
			 */

        } catch (Exception e) {
            System.out.println(id);
            e.printStackTrace();
        }

        return tvProgram;
    }

    // tvIdList 에 포함된 TV Program 들의 정보 리스트 반환
    public ArrayList<TVProgramPreview> getTVProgramList(ArrayList<Long> tvIdList) {

        // 반환값을 담을 TVProgram 리스트 선언
        ArrayList<TVProgramPreview> tvPrograms = new ArrayList<TVProgramPreview>();

        for (long tvId : tvIdList) {
            tvPrograms.add(getTVById(tvId));
        }

        return tvPrograms;
    }
    
	// 넷플릭스에서 방영되는 모든 TV Program 목록 반환
	public ArrayList<TVProgramPreview> getAllTVProgramsByPage(int pageNumber) {

		ArrayList<Long> allTvIdList = allService.getIdListByFile(StaticData.TV_ID_LIST_FILE_PATH);

		// 매칭된 tvId 만 담을 list 초기화
		ArrayList<Long> tvIdList = new ArrayList<Long>();

		// 검색을 시작할 인덱스
		int startIndex = (pageNumber - 1) * count;

		// for (long tvId : tvIdList) {
		for (int i = startIndex; i < startIndex + count; i++) {
			tvIdList.add(allTvIdList.get(i));
		}

		return getTVProgramList(tvIdList);
	}


}
