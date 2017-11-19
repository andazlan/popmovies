package com.andazlan.popmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andazlan on 11/19/17.
 */

// TODO : W3.2 Buat class database helper, class ini harus diturunkan dari SQLiteOpenHelper
public class MovieHelper extends SQLiteOpenHelper {
    // TODO : DECLARE NAMA DB DAN VERSI
    private static final String DATABASE_NAME = "popmovie.db";
    private static final int DATABASE_VERSION = 1;

    // TODO : BUAT CONSTRUKTOR
    public MovieHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // TODO : W3.3 Implement method onCreate dan onUpgrade
    // Fungsi ini akan membuat database di app pada saat app di install
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Buat query untuk membuat table
        String SQL_CREATE_TABLE = "CREATE TABLE " + MovieContract.TABLE + "(" +
                MovieContract.MovieEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                MovieContract.MovieEntry.COLUMN_MOVIE_NAME + " TEXT NOT NULL," +
                MovieContract.MovieEntry.COLUMN_MOVIE_RATING + " INTEGER DEFAULT 0" +
                ")";

        // Setelah query dibuat, jalankan query tersebut
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    // Fungsi ini akan dijalankan pada saat database berubah versi, biasanya ada penambahan kolom,
    // atau perubahan tipe data pada kolum tertentu
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versiOld, int versiNow) {

    }
}
