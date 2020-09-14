package com.my.netflix.aop;

public class StaticData {

    // NETFLIX 에서 방영중인 tv_id 목록이 저장된 file 경로
    public static final String TV_ID_LIST_FILE_PATH = "C:\\Users\\DIR-P-0074\\Desktop\\MyNetflix\\file\\tvIdList.txt";
    //public static final String TV_ID_LIST_FILE_PATH = "D:\\MyNetflix\\file\\tvIdList.txt";

    // NETFLIX 에서 방영중인 movie_id 목록이 저장된 file 경로
    public static final String MOVIE_ID_LIST_FILE_PATH = "C:\\Users\\DIR-P-0074\\Desktop\\MyNetflix\\file\\movieIdList.txt";
    //public static final String MOVIE_ID_LIST_FILE_PATH = "D:\\MyNetflix\\file\\movieIdList.txt";

    public static final String API_MAIN_URL = "https://api.themoviedb.org/3";
    public static final String API_IMAGE_URL = "https://image.tmdb.org/t/p/w300";
    public static final String EMPTY_IMAGE_URL = "http://placehold.it/185x278";
    //public static final String API_IMAGE_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    public static final String API_KEY = "334cc048cf91e9c7e784d8d3241e3b4c";
    public static final String KOREAN = "ko-KR";
    public static final String protocol = "GET";
    
    // 한 페이지에 띄울 TV Program, 영화의 개수
 	public static final int count = 16;

}