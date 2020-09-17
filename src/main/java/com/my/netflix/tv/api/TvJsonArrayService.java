package com.my.netflix.tv.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.netflix.all.api.AllService;
import com.my.netflix.all.model.TVProgram;
import com.my.netflix.aop.Reader;
import com.my.netflix.aop.StaticData;

@Component
public class TvJsonArrayService extends Reader implements TvJsonArray {

	@Autowired
	AllService allService;

	// 한 페이지에 띄울 TV Program, 영화의 개수
	public static final int count = StaticData.count;
	
	
	/* --------------- tv_id -> 객체 -> 리스트 -------------- */
	
	// id 로 TV 프로그램의 모든 정보 TVProgram 객체로 반환
    public TVProgram getTVById(long id) {

        // 반환값을 담을 TVProgram 객체 선언
        TVProgram tvProgram = new TVProgram();
        // season 정보와 genre 정보를 담을 리스트 선언
        ArrayList<Integer> seasons, genreIds;
		ArrayList<String> genreNames;

        try {

            JsonObject tv = allService.getTVJsonById(id);

            // tv_id
            tvProgram.setId(tv.get("id").getAsLong());

            // 제목
            tvProgram.setName(tv.get("name").getAsString());
            // 영상 길이
            try {
                tvProgram.setEpisodeRunTime(tv.get("episode_run_time").getAsInt());
            } catch (Exception e) {
                tvProgram.setEpisodeRunTime(0);
            }

            // 장르
            genreIds = new ArrayList<Integer>();
			genreNames = new ArrayList<String>();

            for (JsonElement element : tv.get("genres").getAsJsonArray()) {
                genreIds.add(element.getAsJsonObject().get("id").getAsInt());
				genreNames.add(element.getAsJsonObject().get("name").getAsString());
            }

            tvProgram.setGenreIds(genreIds);
            tvProgram.setGenreNames(genreNames);

            // 개요
            tvProgram.setOverview(tv.get("overview").getAsString());

            // 포스터 URI
            try {
                tvProgram.setPosterPath(StaticData.API_IMAGE_URL + tv.get("poster_path").getAsString());
            } catch (Exception e) {
                tvProgram.setPosterPath(StaticData.EMPTY_IMAGE_URL);
            }

            // 영상 스트리밍 URL
            tvProgram.setHomepage(tv.get("homepage").getAsString());

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

            // 인기도
            tvProgram.setPopularity(tv.get("popularity").getAsDouble());

            // 시즌 정보
            seasons = new ArrayList<Integer>();

            for (JsonElement element : tv.get("seasons").getAsJsonArray()) {
                seasons.add(element.getAsJsonObject().get("season_number").getAsInt());
            }

            tvProgram.setSeasons(seasons);

            // 종영 여부
            tvProgram.setStatus(tv.get("status").getAsString());

        } catch (Exception e) {
            System.out.println(id);
            e.printStackTrace();
        }

        return tvProgram;
    }
	
	// tvIdList 에 포함된 TV Program 들의 정보 리스트 반환
	
    public ArrayList<TVProgram> getTVProgramList(ArrayList<Long> tvIdList) {

        // 반환값을 담을 TVProgram 리스트 선언
        ArrayList<TVProgram> tvPrograms = new ArrayList<TVProgram>();

        for (long tvId : tvIdList) {
            tvPrograms.add(getTVById(tvId));
        }

        return tvPrograms;
    }
	
	
	
	/* ------ 장르별 검색 -------- */

	// 장르 id 목록에 매칭되는 TV Program 목록 반환
	
	@Override
	public ArrayList<TVProgram> getTVProgramsByGenreIds(int pageNumber, ArrayList<Integer> genreIds) {

		String url = allService.searchTvByGenreUrl(pageNumber, genreIds);

		return getTVProgramList(allService.getIdListByUrl(url));
	}
	

	/* ------ 연도별 검색 -------- */

	// 연도별 TV Program 목록 반환

	@Override
	public ArrayList<TVProgram> getTVProgramsByYear(int pageNumber, String year) {
		
		String url = allService.searchTvByYearUrl(pageNumber, year);
		
		return getTVProgramList(allService.getIdListByUrl(url));
	}
	
	
	/* ------ 인기순 검색 -------- */

	// 인기순 - 내림차순 TV Program 목록 반환	

	@Override
	public ArrayList<TVProgram> getPopularDescTVPrograms(int pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 인기순 - 오름차순 TV Program 목록 반환

	@Override
	public ArrayList<TVProgram> getPopularAscTVPrograms(int pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/* ------ 방영일순 검색 -------- */

	// 최신순 TV Program 목록 반환

	@Override
	public ArrayList<TVProgram> getLatestTVPrograms(int pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 오래된순 TV Program 목록 반환
	
	@Override
	public ArrayList<TVProgram> getOldestTVPrograms(int pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/* ------------------ 공통 부분 -------------------- */


	@Override
	// 표시될 TV Program 의 총 개수 반환 - 장르별 검색일 때
	public int getCountPage(int pageNumber, ArrayList<Integer> genreIds) {

		String url = allService.searchTvByGenreUrl(pageNumber, genreIds);

		return getCount(url);
	}
	
	@Override
	// 표시될 TV Program 의 총 개수 반환 - 연도별 검색일 때
	public int getCountPage(int pageNumber, String year) {

		String url = allService.searchTvByYearUrl(pageNumber, year);

		return getCount(url);
	}
	
	// id로 TV Program 객체 반환

	@Override
	public TVProgram getTVProgramById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
