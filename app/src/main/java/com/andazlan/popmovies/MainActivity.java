package com.andazlan.popmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO : Buat retrofitnya
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Const.getUrlPrefix())
                .build();

        //TODO : Buat api
        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);
        tmdbApi.getDetailMovie(550, Const.API_KEY).enqueue(new Callback<JsonObject>() {
            //TODO : jika berhasil maka yang dijalankan onResponse
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "Ambil data berhasil : " + response.body().toString());
            }

            //TODO : jika gagal maka yang dijalankan onFailure
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "Ambil data gagal : " + t.getMessage());
            }
        });
    }
}
