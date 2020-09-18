package com.my.netflix.tv.api;

import java.util.ArrayList;

import com.my.netflix.tv.model.TVProgram;
import com.my.netflix.tv.model.TVProgramPreview;

public interface TVAPI {

	/* --------------- 인기순 검색 ----------------- */
	
	// 인기순 - 내림차순 TVProgramPreview 목록 반환
	public ArrayList<TVProgramPreview> getPopularDescTVPrograms(int pageNumber);
	
	// 인기순 - 오름차순 TVProgramPreview 목록 반환
	public ArrayList<TVProgramPreview> getPopularAscTVPrograms(int pageNumber);
	
	/* ---------------- 방영일순 검색 ---------------- */
	
	// 최신순 TVProgramPreview 목록 반환
	public ArrayList<TVProgramPreview> getLatestTVPrograms(int pageNumber);
	
	// 오래된순 TVProgramPreview 목록 반환
	public ArrayList<TVProgramPreview> getOldestTVPrograms(int pageNumber);
	
	/* -------------- 장르별 검색 ----------------- */
	
	// 장르 id 목록에 매칭되는 TVProgramPreview 목록 반환
	public ArrayList<TVProgramPreview> getTVProgramsByGenreIds(int pageNumber, ArrayList<Integer> genreIds);
	
	/* --------------- 연도별 검색 ----------------- */
	
	// 연도별 TVProgramPreview 목록 반환
	public ArrayList<TVProgramPreview> getTVProgramsByYear(int pageNumber, String year);

	/* ------------ 페이지 데이터 개수 반환 ------------- */

	public int getCountPage(int pageNumber, int condition);

	public int getCountPage(int pageNumber, ArrayList<Integer> genreIds);
	
	public int getCountPage(int pageNumber, String year);
	
	/* ----------------- 공통 부분 ------------------ */
	
	public TVProgramPreview getTVPreviewById(long id);

	public ArrayList<TVProgramPreview> getTVPreviewList(ArrayList<Long> tvIdList);

	public TVProgram getTVProgramById(long id);
	
	public ArrayList<TVProgram> getTVProgramList(ArrayList<Long> tvIdList);
	
}
