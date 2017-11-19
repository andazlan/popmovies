package com.andazlan.popmovies.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by andazlan on 11/19/17.
 */

//TODO : W3.1 Buat kontrak movie, dimana kontrak ini akan menjadi "kesepakatan" antara database dengan yang akan mengakses database
public final class MovieContract {
    public static String TABLE = "movie";

    public static String AUTHORITY = "com.andazlan.popmovies";
    public static String PATH = "movies";

    public static Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class MovieEntry implements BaseColumns{
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_MOVIE_NAME = "name";
        public final static String COLUMN_MOVIE_RATING = "rating";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();
    }
}
