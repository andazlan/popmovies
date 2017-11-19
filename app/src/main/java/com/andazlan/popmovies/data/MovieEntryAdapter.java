package com.andazlan.popmovies.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.andazlan.popmovies.R;

/**
 * Created by andazlan on 11/19/17.
 */

public class MovieEntryAdapter extends CursorAdapter {

    public MovieEntryAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.entry_movie_utem, viewGroup,
                false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nama = view.findViewById(R.id.txt_nama);
        TextView rating = view.findViewById(R.id.txt_rating);

        int namaColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_NAME);
        int ratingColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_RATING);

        nama.setText(cursor.getString(namaColumnIndex));
        rating.setText(String.valueOf(cursor.getInt(ratingColumnIndex)));
    }
}
