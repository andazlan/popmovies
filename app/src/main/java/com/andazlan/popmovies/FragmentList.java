package com.andazlan.popmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andazlan.popmovies.list.ListMovieResponse;
import com.andazlan.popmovies.list.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andazlan on 11/11/17.
 */

// TODO : 2. Buat class baru, turunkan dari Fragment (pakai yang support library)
public class FragmentList extends Fragment {
    public static String TAG = FragmentList.class.getSimpleName();
    private RecyclerView mListMovie;
    private MovieAdapter mAdapter;
    // TODO : 3. Buat constructornya
    public static FragmentList createInstance(String nama, int id){
        FragmentList fragmentList = new FragmentList();
        Bundle bundle = new Bundle();
        bundle.putString("nama", nama);
        bundle.putInt("id", 5);
        fragmentList.setArguments(bundle);
        return fragmentList;
    }


    public FragmentList() {
    }

    // TODO : 4. Override function onCreateView untuk melakukan kostumasi view
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // TODO : 5. Buat view yang sudah dibuat layoutnya
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_list, container,
                false);

        mListMovie = rootView.findViewById(R.id.list_movie);

        // TODO : Jika memakai RecyclerView, jangan lupa set Layout Manager ke list!
        mListMovie.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new MovieAdapter();
        mListMovie.setAdapter(mAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Const.getUrlPrefix())
                .build();

        //TODO : Buat api
        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);
        tmdbApi.getListMovie(Const.API_KEY).enqueue(new Callback<ListMovieResponse>() {
            @Override
            public void onResponse(Call<ListMovieResponse> call, Response<ListMovieResponse> response) {
                String titles = "";
                if (response.body().getResults() != null && response.body().getResults().size() > 0){
                    for (int i = 0; i < response.body().getResults().size(); i++) {
                        Result result = response.body().getResults().get(i);
                        titles += result.getTitle() + "\n";

                    }

                    mAdapter.setMovies(response.body().getResults());
                    mAdapter.notifyDataSetChanged();

                    Log.d(TAG, titles);
                }
            }

            @Override
            public void onFailure(Call<ListMovieResponse> call, Throwable t) {

            }
        });
        /*
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
        */
        /*
        TextView nama = rootView.findViewById(R.id.txt_nama);
        if (getArguments() != null && !getArguments().getString("nama").isEmpty()){
            nama.setText(getArguments().getString("nama"));
        }
        */

        // TODO : 6. Jangan lupa view yang dibuat dibalikin
        return rootView;
    }


}
