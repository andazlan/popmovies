package com.andazlan.popmovies;

import com.andazlan.popmovies.list.ListMovieResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by andazlan on 11/4/17.
 */

public interface TmdbApi {
    @GET("3/movie/{id}")
    Call<JsonObject> getDetailMovie(@Path("id") int id, @Query("api_key") String apiKey);

    //TODO : Tambahkan kontrak untuk mengambill list movie
    @GET("3/discover/movie")
    Call<ListMovieResponse> getListMovie(@Query("api_key") String apiKey);
}

