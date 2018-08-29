package com.example.anxdre.ifilm.data;

public class API {
    private static String KEY = "dc5014f04e30c1ffc6412147813f161d";
    private static String BASE_URL = "http://api.themoviedb.org/3/";
    public static String MOVIE_POPULAR = BASE_URL + "movie/popular?api_key=" + KEY + "&language=en-US&page=1";
    public static String MOVIE_UPCOMING = BASE_URL + "movie/upcoming?api_key=" + KEY + "&language=en-US&page=1";

    public static String MOVIE_VIDEO_BASE = BASE_URL + "movie/";
    public static String MOVIE_VIDEO_URL = "/videos?api_key=" + KEY + "&language=en-US";

    public static String NOW_PLAYING = BASE_URL + "movie/now_playing?api_key=" + KEY + "&language=en-US&page=1";
    public static String POSTER_PATH = "https://image.tmdb.org/t/p/";

    public static String MOVIE_DESC = "?api_key=" + KEY + "&language=en-US";

}
