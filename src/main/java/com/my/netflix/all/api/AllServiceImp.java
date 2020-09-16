package com.my.netflix.all.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.netflix.aop.Reader;
import com.my.netflix.aop.StaticData;
import com.my.netflix.home.model.MainContent;
import com.my.netflix.model.Movie;

@Component
public class AllServiceImp extends Reader implements AllService {
	
	
	@Override
	public void getMainContentList(ArrayList<Long> mainContentIdList) {
//		// 반환값을 담을 Movie 객체 선언
//        MainContent mainContent = new MainContent();
//
//        try {
//
//            JsonObject mv = getMovieJsonById(id);
//
//            // movie_id
//            movie.setId(mv.get("id").getAsLong());
//
//            // 제목
//            movie.setTitle(mv.get("title").getAsString());
//
//            // 영상 길이
//            try {
//                movie.setRuntime(mv.get("runtime").getAsInt());
//            } catch (Exception e) {
//                movie.setRuntime(0);
//            }
//
//            // 장르
//            genres = new ArrayList<Integer>();
//
//            for (JsonElement element : mv.get("genres").getAsJsonArray()) {
//                genres.add(element.getAsJsonObject().get("id").getAsInt());
//            }
//
//            movie.setGenres(genres);
//
//            // 개요
//            movie.setOverview(mv.get("overview").getAsString());
//
//            // 포스터 URI
//            try {
//                movie.setPosterPath(StaticData.API_IMAGE_URL + mv.get("poster_path").getAsString());
//            } catch (Exception e) {
//                movie.setPosterPath(StaticData.EMPTY_IMAGE_URL);
//            }
//
//            // 영상 스트리밍 URL
//            try {
//                movie.setHomepage(mv.get("homepage").getAsString());
//            } catch (Exception e) {
//                movie.setHomepage(null);
//            }
//
//            // 방영일 정보
//            try {
//                movie.setReleaseDate(mv.get("release_date").getAsString());
//            } catch (Exception e) {
//                movie.setReleaseDate(null);
//            }
//
//            // 인기도
//            movie.setPopularity(mv.get("popularity").getAsDouble());
//
//            // 종영 여부
//            movie.setStatus(mv.get("status").getAsString());
//
//        } catch (Exception e) {
//            System.out.println(id);
//            e.printStackTrace();
//        }
//
//        return movie;
	}
	

	/* ------    Movie     ------- */    

    // id 로 프로그램의 모든 정보 JsonObject 로 반환
    public JsonObject getMovieJsonById(long id) {

        String url = StaticData.API_MAIN_URL;
        url += "/movie/" + id;
        url += "?api_key=" + StaticData.API_KEY;
        url += "&language=" + StaticData.KOREAN;

        getReader(url);

        return getJson();
    }

    // id 로 프로그램의 모든 정보 Movie 객체로 반환
    public Movie getMovieById(long id) {

        // 반환값을 담을 Movie 객체 선언
        Movie movie = new Movie();
        // genre 정보를 담을 리스트 선언
        ArrayList<Integer> genres;

        try {

            JsonObject mv = getMovieJsonById(id);

            // movie_id
            movie.setId(mv.get("id").getAsLong());

            // 제목
            movie.setTitle(mv.get("title").getAsString());

            // 영상 길이
            try {
                movie.setRuntime(mv.get("runtime").getAsInt());
            } catch (Exception e) {
                movie.setRuntime(0);
            }

            // 장르
            genres = new ArrayList<Integer>();

            for (JsonElement element : mv.get("genres").getAsJsonArray()) {
                genres.add(element.getAsJsonObject().get("id").getAsInt());
            }

            movie.setGenres(genres);

            // 개요
            movie.setOverview(mv.get("overview").getAsString());

            // 포스터 URI
            try {
                movie.setPosterPath(StaticData.API_IMAGE_URL + mv.get("poster_path").getAsString());
            } catch (Exception e) {
                movie.setPosterPath(StaticData.EMPTY_IMAGE_URL);
            }

            // 영상 스트리밍 URL
            try {
                movie.setHomepage(mv.get("homepage").getAsString());
            } catch (Exception e) {
                movie.setHomepage(null);
            }

            // 방영일 정보
            try {
                movie.setReleaseDate(mv.get("release_date").getAsString());
            } catch (Exception e) {
                movie.setReleaseDate(null);
            }

            // 인기도
            movie.setPopularity(mv.get("popularity").getAsDouble());

            // 종영 여부
            movie.setStatus(mv.get("status").getAsString());

        } catch (Exception e) {
            System.out.println(id);
            e.printStackTrace();
        }

        return movie;
    }

    // movieIdList 에 포함된 영화들의 정보 리스트 반환
    public ArrayList<Movie> getMovieList(ArrayList<Long> movieIdList) {

        // 반환값을 담을 Movie 리스트 선언
        ArrayList<Movie> movies = new ArrayList<Movie>();

        for (long movieId : movieIdList) {
            movies.add(getMovieById(movieId));
        }

        return movies;
    }


    /* ------    TV Program     ------- */

    // id 로 TV 프로그램의 모든 정보 JsonObject 로 반환
    public JsonObject getTVJsonById(long id) {

        String url = StaticData.API_MAIN_URL;
        url += "/tv/" + id;
        url += "?api_key=" + StaticData.API_KEY;
        url += "&language=" + StaticData.KOREAN;

        getReader(url);

        return getJson();
    }

    // 인기 TV 프로그램의 idList 반환 - 페이징
    
    public ArrayList<Long> getPopularTVProgramIdList(int pageNumber) {

        String url = StaticData.API_MAIN_URL;
        url += "/tv/popular";
        url += "?api_key=" + StaticData.API_KEY;
        url += "&language=" + StaticData.KOREAN;
        url += "&page=" + pageNumber;

        return getIdListByUrl(url);
    }

    
    /* 공통 */
    
    // 파일에 담긴 id 값을 추출해서 IdList 로 반환
    
    public ArrayList<Long> getIdListByFile(String filePath) {

        FileReader fr = null;
        BufferedReader br = null;
        StringTokenizer st;

        String line;
        ArrayList<Long> idList = new ArrayList<Long>();

        try {
            fr = new FileReader(new File(filePath));
            br = new BufferedReader(fr);

            // file 한줄씩 읽기
            while ((line = br.readLine()) != null) {

                // StringTokenizer 에 한 줄 담기
                st = new StringTokenizer(line);

                // StringTokenizer 에 담긴 토큰을 list 에 추가
                while (st.hasMoreTokens()) {
                	idList.add(Long.parseLong(st.nextToken()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                if (fr != null) fr.close();
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return idList;
    }
    
    
    // JsonArray 값에 담긴 id 값을 추출해서 IdList 로 반환
    
    public ArrayList<Long> getIdListByUrl(String url) {
    	
    	ArrayList<Long> idList = new ArrayList<Long>();

        for(JsonElement jsonElement : getResults(url)) {
        	idList.add(jsonElement.getAsJsonObject().get("id").getAsLong());
        }

        return idList;
    }

    
    // 장르 id List 반환
    
    public ArrayList<Integer> getGenreIdList(String url) {
    	
    	ArrayList<Integer> genreIdList = new ArrayList<Integer>();

        for(JsonElement jsonElement : getResults(url)) {
        	genreIdList.add(jsonElement.getAsJsonObject().get("id").getAsInt());
        }

        return genreIdList;
    }

	
    
}
