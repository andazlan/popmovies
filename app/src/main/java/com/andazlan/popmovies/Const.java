package com.andazlan.popmovies;

/**
 * Created by andazlan on 11/4/17.
 */

public class Const {
    public static final String URL_PREFIX = "http://";
    public static final String BASE_URL = "api.themoviedb.org";
    public static final String API_KEY = "06b1dee36e905e5ad1e0d97cfac3f277";

    public static String getUrlPrefix(){
        return URL_PREFIX + BASE_URL;
    }
}
