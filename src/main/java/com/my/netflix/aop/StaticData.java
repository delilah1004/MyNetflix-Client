package com.my.netflix.aop;

public class StaticData {
	
	public static final long[] HOME_POPULAR_DESC_MOVIE_ID_LIST = {734309, 438396, 605116, 611395, 632618, 547016, 38700, 614696, 623491, 545609, 583083, 512200, 644090, 500840, 666750, 
			429617, 689249, 667520, 284054, 718867, 658777, 726664, 200085, 339095, 509967, 338967, 612706, 372058, 412117, 706503};
	
	public static final long[] HOME_LATEST_MOVIE_ID_LIST = {737802, 623491, 739227, 658777, 644090, 736517, 735364, 734309, 615466, 500840, 438396, 736759, 726887, 730428, 626393, 
			632618, 655832, 732577, 726940, 726664, 605116, 645489, 725082, 726661, 612706, 726649, 726658, 725084, 695596, 729259};
	
	public static final long[] HOME_POPULAR_DESC_TV_ID_LIST = {63174, 77169, 48866, 75006, 71446, 60735, 105214, 70523, 1416, 69050, 89233, 1402, 76669, 1396, 60625, 
			70785, 76719, 66732, 34524, 44217, 1413, 104555, 1412, 46896, 1403, 7225, 62688, 2190, 60708, 1418};
	
	public static final long[] HOME_LATEST_TV_ID_LIST = {107363, 106292, 87846, 108268, 107904, 99047, 89233, 91545, 107671, 108824, 107625, 106753, 107422, 106238, 107367, 
			100074, 107011, 106754, 104555, 90766, 106530, 107304, 106528, 106526, 106514, 105797, 106513, 106173, 106359, 106525};
	
	/* --------- 공통 정보 ---------- */
	
    public static final String API_MAIN_URL = "https://api.themoviedb.org/3";
    public static final String API_IMAGE_URL = "https://image.tmdb.org/t/p/w300";
    public static final String EMPTY_IMAGE_URL = "http://placehold.it/185x278";
    public static final String BACKGROUND_IMAGE_URL = "//image.tmdb.org/t/p/w1920_and_h800_multi_faces";
	public static final String EMPTY_BACKGROUND_IMAGE_URL = "//placehold.it/1920x800";

    public static final String API_KEY = "334cc048cf91e9c7e784d8d3241e3b4c";
    public static final String KOREAN = "ko-KR";
    public static final String protocol = "GET";
    
    // 한 페이지에 띄울 TV Program, 영화의 개수
 	public static final int count = 20;

}