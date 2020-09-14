package com.my.netflix.test.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.netflix.aop.Reader;
import com.my.netflix.aop.StaticData;
import com.my.netflix.model.Movie;
import com.my.netflix.model.TVProgram;

@Component
public class AllServiceImp extends Reader implements AllService {
	
	/* ------    Movie     ------- */

    // 파일을 불러와 allMovieIdList 정보 업데이트
    // 넷플릭스에서 방영되는 모든 영화들의 movie_id 목록 반환
    public ArrayList<Long> getAllMovieIds() {

        FileReader fr = null;
        BufferedReader br = null;
        StringTokenizer st;

        String line;
        ArrayList<Long> allMovieIdList = new ArrayList<Long>();

        try {
            fr = new FileReader(new File(StaticData.MOVIE_ID_LIST_FILE_PATH));
            br = new BufferedReader(fr);

            // file 한줄씩 읽기
            while ((line = br.readLine()) != null) {

                // StringTokenizer 에 한 줄 담기
                st = new StringTokenizer(line);

                // StringTokenizer 에 담긴 토큰을 list 에 추가
                while (st.hasMoreTokens()) {
                    allMovieIdList.add(Long.parseLong(st.nextToken()));
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

        return allMovieIdList;
    }

    // id 로 프로그램의 모든 정보 JsonObject 로 반환
    public JsonObject getMovieById(long id) {

        String url = StaticData.API_MAIN_URL;
        url += "/movie/" + id;
        url += "?api_key=" + StaticData.API_KEY;
        url += "&language=" + StaticData.KOREAN;

        getReader(url);

        return getJson();
    }

    // movieIdList 에 포함된 영화들의 정보 리스트 반환
    public ArrayList<Movie> getMovieList(ArrayList<Long> movieIdList) {

        // 반환값을 담을 Movie 리스트 선언
        ArrayList<Movie> movies = new ArrayList<Movie>();
        // genre 정보를 담을 리스트 선언
        ArrayList<Integer> genres;

        for (long movieId : movieIdList) {

            try {
                JsonObject mv = getMovieById(movieId);

                Movie movie = new Movie();

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

                movies.add(movie);

            } catch (Exception e) {
                System.out.println(movieId);
                e.printStackTrace();
            }
        }

        return movies;
    }

    // 인기 영화의 모든 정보 JsonArray 로 반환
    public JsonArray getPopularMovieIdList(int pageNumber) {

        String url = StaticData.API_MAIN_URL;
        url += "/movie/popular";
        url += "?api_key=" + StaticData.API_KEY;
        url += "&language=" + StaticData.KOREAN;
        url += "&page=" + pageNumber;

        getReader(url);

        return getJson().get("results").getAsJsonArray();
    }
	
	
	
	
	/* ------    TV Program     ------- */

    // 파일을 불러와 allTvIdList 정보 업데이트
    // 넷플릭스에서 방영되는 모든 TV Program 들의 tv_id 목록 반환
    public ArrayList<Long> getAllTVIds() {

        FileReader fr = null;
        BufferedReader br = null;
        StringTokenizer st;

        String line;
        ArrayList<Long> allTvIdList = new ArrayList<Long>();

        try {
            fr = new FileReader(new File(StaticData.TV_ID_LIST_FILE_PATH));
            br = new BufferedReader(fr);

            // file 한줄씩 읽기
            while ((line = br.readLine()) != null) {

                // StringTokenizer 에 한 줄 담기
                st = new StringTokenizer(line);

                // StringTokenizer 에 담긴 토큰을 list 에 추가
                while (st.hasMoreTokens()) {
                    allTvIdList.add(Long.parseLong(st.nextToken()));
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

        return allTvIdList;
    }

    // id 로 TV 프로그램의 모든 정보 JsonObject 로 반환
    public JsonObject getTVById(long id) {

        String url = StaticData.API_MAIN_URL;
        url += "/tv/" + id;
        url += "?api_key=" + StaticData.API_KEY;
        url += "&language=" + StaticData.KOREAN;

        getReader(url);

        return getJson();
    }

    // 인기 TV 프로그램의 모든 정보 JsonObject 로 반환
    public JsonArray getPopularTVProgramIdList(int pageNumber) {

        String url = StaticData.API_MAIN_URL;
        url += "/tv/popular";
        url += "?api_key=" + StaticData.API_KEY;
        url += "&language=" + StaticData.KOREAN;
        url += "&page=" + pageNumber;

        getReader(url);

        return getJson().get("results").getAsJsonArray();
    }

    // tvIdList 에 포함된 TV Program 들의 정보 리스트 반환
    public ArrayList<TVProgram> getTVProgramList(ArrayList<Long> tvIdList) {

        // 반환값을 담을 TVProgram 리스트 선언
        ArrayList<TVProgram> tvPrograms = new ArrayList<TVProgram>();
        // season 정보와 genre 정보를 담을 리스트 선언
        ArrayList<Integer> seasons, genres;

        for (long tvId : tvIdList) {

            try {
                JsonObject tv = getTVById(tvId);

                TVProgram tvProgram = new TVProgram();

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
                genres = new ArrayList<Integer>();

                for (JsonElement element : tv.get("genres").getAsJsonArray()) {
                    genres.add(element.getAsJsonObject().get("id").getAsInt());
                }

                tvProgram.setGenres(genres);

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

                tvPrograms.add(tvProgram);
            } catch (Exception e) {
                System.out.println(tvId);
                e.printStackTrace();
            }
        }

        return tvPrograms;
    }

}
