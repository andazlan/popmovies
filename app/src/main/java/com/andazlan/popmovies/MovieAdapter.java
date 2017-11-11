package com.andazlan.popmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.andazlan.popmovies.list.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andazlan on 11/11/17.
 */

// TODO : 1. Buat class baru dan turunkan dari RecyclerView.Adapter, dan attach view holdernya
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    private List<Result> mMovies;

    public MovieAdapter() {
        mMovies = null;
    }

    //TODO : 3. Implement 3 method Recyclerview
    //Todo : 4. Buat view holder menggunakan view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Buat view untuk view holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);

        // Buat view holdernya
        ViewHolder viewHolder = new ViewHolder(view);

        // Jangan lupa holder yang dibentuk, dibalikin
        return viewHolder;
    }

    //TODO : 5. Tampilkan data menggunakan holder yang isinya widget2
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result movie = getMovies().get(position);
        holder.title.setText(movie.getTitle());
    }

    public List<Result> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Result> movies) {
        this.mMovies = movies;
    }

    @Override
    public int getItemCount() {
        if (mMovies == null){
            return 0;
        }
        return mMovies.size();
    }

    //TODO : 2. Buat view holdernya
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView gambar;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title);
        }
    }
}
