package com.my.netflix.tv.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.netflix.all.api.AllService;
import com.my.netflix.all.model.Genre;
import com.my.netflix.aop.Reader;
import com.my.netflix.aop.StaticData;
import com.my.netflix.tv.model.Season;
import com.my.netflix.tv.model.TVProgram;
import com.my.netflix.tv.model.TVProgramPreview;

@Component
public class TVAPIService extends Reader implements TVAPI {

	@Autowired
	AllService allService;

	// 한 페이지에 띄울 TV Program, 영화의 개수
	public static final int count = StaticData.count;

	/* ------ 인기순 검색 -------- */

	// 인기순 - 내림차순 TV Program 목록 반환
	
	@Override
	public ArrayList<TVProgramPreview> getPopularDescTVPrograms(int pageNumber) {

		String url = allService.searchTvByPopularDesc(pageNumber);

		return getTVPreviewList(allService.getIdListByUrl(url));
	}

	// 인기순 - 오름차순 TV Program 목록 반환
	
	@Override
	public ArrayList<TVProgramPreview> getPopularAscTVPrograms(int pageNumber) {

		String url = allService.searchTvByPopularAsc(pageNumber);

		return getTVPreviewList(allService.getIdListByUrl(url));
	}

	/* ------ 방영일순 검색 -------- */

	// 최신순 TV Program 목록 반환

	@Override
	public ArrayList<TVProgramPreview> getLatestTVPrograms(int pageNumber) {

		String url = allService.searchTvLatest(pageNumber);

		return getTVPreviewList(allService.getIdListByUrl(url));
	}

	// 오래된순 TV Program 목록 반환

	@Override
	public ArrayList<TVProgramPreview> getOldestTVPrograms(int pageNumber) {

		String url = allService.searchTvOldest(pageNumber);

		return getTVPreviewList(allService.getIdListByUrl(url));
	}

	/* ------ 장르별 검색 -------- */

	// 장르 id 목록에 매칭되는 TV Program 목록 반환
	@Override
	public ArrayList<TVProgramPreview> getTVProgramsByGenreId(int pageNumber, int genreId) {

		String url = allService.searchTvByGenreUrl(pageNumber, genreId);

		return getTVPreviewList(allService.getIdListByUrl(url));
	}

	/* ------ 연도별 검색 -------- */

	// 연도별 TV Program 목록 반환

	@Override
	public ArrayList<TVProgramPreview> getTVProgramsByYear(int pageNumber, String year) {

		String url = allService.searchTvByYearUrl(pageNumber, year);

		return getTVPreviewList(allService.getIdListByUrl(url));
	}

	/* ------------------ 페이지 데이터 개수 반환 -------------------- */

	@Override
	// 표시될 TV Program 의 총 개수 반환 - 인기순 / 날짜순
	public int getCountPage(int pageNumber, int condition) {

		String url = null;

		switch (condition) {

		// 인기 내림차순
		case 0:
			url = allService.searchTvByPopularDesc(pageNumber);
			break;

		// 인기 오름차순
		case 1:
			url = allService.searchTvByPopularAsc(pageNumber);
			break;

		// 최신순
		case 2:
			url = allService.searchTvLatest(pageNumber);
			break;

		// 오래된순
		case 3:
			url = allService.searchTvOldest(pageNumber);
			break;
		}

		return getCount(url);
	}

	// 표시될 TV Program 의 총 개수 반환 - 장르별 검색
	@Override
	public int getCountPageByGenre(int pageNumber, int genreId) {

		String url = allService.searchTvByGenreUrl(pageNumber, genreId);

		return getCount(url);
	}

	// 표시될 TV Program 의 총 개수 반환 - 연도별 검색
	@Override
	public int getCountPage(int pageNumber, String year) {

		String url = allService.searchTvByYearUrl(pageNumber, year);

		return getCount(url);
	}

	/* ------------------ 공통 부분 -------------------- */

	// Json 데이터 TVProgramPreview 객체에 저장
	public TVProgramPreview getTVPreviewById(long id) {

		TVProgramPreview tvPreview = new TVProgramPreview();

		ArrayList<Genre> genres = null;
		Genre genre = null;

		try {

			JsonObject tv = allService.getTVJsonById(id);

			// tv_id
			tvPreview.setId(tv.get("id").getAsLong());

			// 포스터 URI
			try {
				tvPreview.setPosterPath(StaticData.API_IMAGE_URL + tv.get("poster_path").getAsString());
			} catch (Exception e) {
				tvPreview.setPosterPath(StaticData.EMPTY_IMAGE_URL);
			}

			// 제목
			tvPreview.setName(tv.get("name").getAsString());

			// 첫 방영일 정보
			try {
				tvPreview.setFirstAirDate(tv.get("first_air_date").getAsString());
			} catch (Exception e) {
				tvPreview.setFirstAirDate(null);
			}

			// 마지막 방영일 정보
			try {
				tvPreview.setLastAirDate(tv.get("last_air_date").getAsString());
			} catch (Exception e) {
				tvPreview.setLastAirDate(null);
			}

			// 장르
			genres = new ArrayList<Genre>();

			for (JsonElement element : tv.get("genres").getAsJsonArray()) {
				genre = new Genre();
				genre.setId(element.getAsJsonObject().get("id").getAsInt());
				genre.setName(element.getAsJsonObject().get("name").getAsString());
				genres.add(genre);
			}

			tvPreview.setGenres(genres);

		} catch (Exception e) {
			System.out.println(id);
			e.printStackTrace();
		}

		return tvPreview;
	}

	// TVProgramPreview 객체 리스트 생성
	public ArrayList<TVProgramPreview> getTVPreviewList(ArrayList<Long> tvIdList) {

		// 반환값을 담을 TVProgramPreview 리스트 선언
		ArrayList<TVProgramPreview> tvPreviewList = new ArrayList<TVProgramPreview>();

		for (long tvId : tvIdList) {
			tvPreviewList.add(getTVPreviewById(tvId));
		}

		return tvPreviewList;
	}

	// Json 데이터 TVProgram 객체에 저장
	public TVProgram getTVProgramById(long id) {

		TVProgram tvProgram = new TVProgram();

		ArrayList<Genre> genres = null;
		Genre genre = null;
		
		ArrayList<Season> seasons = null;
		Season season = null;

		try {

			JsonObject tv = allService.getTVJsonById(id);

			// tv_id
			tvProgram.setId(tv.get("id").getAsLong());

			// 포스터 URI
			try {
				tvProgram.setPosterPath(StaticData.API_IMAGE_URL + tv.get("poster_path").getAsString());
			} catch (Exception e) {
				tvProgram.setPosterPath(StaticData.EMPTY_IMAGE_URL);
			}

			// backdrop URI
			try {
				tvProgram.setBackdropPath(StaticData.BACKGROUND_IMAGE_URL + tv.get("backdrop_path").getAsString());
			} catch (Exception e) {
				tvProgram.setPosterPath(StaticData.EMPTY_BACKGROUND_IMAGE_URL);
			}

			// 제목
			tvProgram.setName(tv.get("name").getAsString());

			// 첫 방영일 정보
			try {
				tvProgram.setFirstAirDate(tv.get("first_air_date").getAsString());
			} catch (Exception e) {
				tvProgram.setFirstAirDate(null);
			}

			// 마지막 방영일 정보
			try {
				tvProgram.setLastAirDate(tv.get("last_air_date").getAsString());
			} catch (Exception e) {
				tvProgram.setLastAirDate(null);
			}

			// 장르
			genres = new ArrayList<Genre>();

			for (JsonElement element : tv.get("genres").getAsJsonArray()) {
				genre = new Genre();
				genre.setId(element.getAsJsonObject().get("id").getAsInt());
				genre.setName(element.getAsJsonObject().get("name").getAsString());
				genres.add(genre);
			}

			tvProgram.setGenres(genres);

			// 시즌
			seasons = new ArrayList<Season>();

			for (JsonElement element : tv.get("seasons").getAsJsonArray()) {
				season = new Season();
				
				season.setId(element.getAsJsonObject().get("id").getAsInt());
				
				try {
					season.setPosterPath(StaticData.API_IMAGE_URL + tv.get("poster_path").getAsString());
				} catch (Exception e) {
					season.setPosterPath(StaticData.EMPTY_IMAGE_URL);
				}
				
				season.setName(element.getAsJsonObject().get("name").getAsString());
				season.setAirDate(element.getAsJsonObject().get("air_date").getAsString());
				season.setEpisodeCount(element.getAsJsonObject().get("episode_count").getAsInt());
				
				seasons.add(season);
			}
			
			tvProgram.setSeasons(seasons);

			// 개요
			tvProgram.setOverview(tv.get("overview").getAsString());

			// 영상 스트리밍 URL
			tvProgram.setHomepage(tv.get("homepage").getAsString());

			// 종영 여부 - Ended, Returning Series
			String status = tv.get("status").getAsString();
			
			switch (status) {
			case "Returning Series":
				tvProgram.setStatus("다음 시즌 방영 예정");
				break;
			case "Ended" :
				tvProgram.setStatus("종영");
				break;
			}

		} catch (Exception e) {
			System.out.println(id);
			e.printStackTrace();
		}

		return tvProgram;
	}

	// TVProgram 객체 리스트 생성
	public ArrayList<TVProgram> getTVProgramList(ArrayList<Long> tvIdList) {
		
		ArrayList<TVProgram> tvProgramList = new ArrayList<TVProgram>();

		for (long tvId : tvIdList) {
			tvProgramList.add(getTVProgramById(tvId));
		}

		return tvProgramList;
	}

}
