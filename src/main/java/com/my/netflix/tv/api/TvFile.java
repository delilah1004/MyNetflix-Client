package com.my.netflix.tv.api;

import java.util.ArrayList;

import com.my.netflix.tv.model.TVProgramPreview;

public interface TvFile {

	public ArrayList<TVProgramPreview> getAllTVProgramsByPage(int pageNumber);
	
	public TVProgramPreview getTVById(long id);
	
	public ArrayList<TVProgramPreview> getTVProgramList(ArrayList<Long> tvIdList);
	

	/* ------ 장르별 검색 -------- */

	// 장르 id 목록에 매칭되는 TV Program 목록 반환
	public ArrayList<TVProgramPreview> getTVProgramsByGenreIds(long lastId, ArrayList<Integer> genreIds);

	/* ------ 연도별 검색 -------- */

	// 연도별 TV Program 목록 반환
	public ArrayList<TVProgramPreview> getTVProgramsByYear(long lastId, String year);

	/* ------ 인기순 검색 -------- */

	// 인기순 - 내림차순 TV Program 목록 반환
	public ArrayList<TVProgramPreview> getPopularDescTVPrograms(int pageNumber);

	// 인기순 - 오름차순 TV Program 목록 반환
	public ArrayList<TVProgramPreview> getPopularAscTVPrograms(int pageNumber);

	/* ------ 방영일순 검색 -------- */

	// 최신순 TV Program 목록 반환
	public ArrayList<TVProgramPreview> getLatestTVPrograms(int pageNumber);

	// 오래된순 TV Program 목록 반환
	public ArrayList<TVProgramPreview> getOldestTVPrograms(int pageNumber);

	/* ------------------ 공통 부분 -------------------- */

	public int getCountPage(int condition);
}
