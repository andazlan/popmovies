package com.andazlan.popmovies.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by andazlan on 11/19/17.
 */

public class MovieEntryProvider extends ContentProvider {
    private final static int MOVIE = 100;
    private final static int MOVIE_WITH_ID = 101;

    private UriMatcher mUriMatcher = getUriMatcher();

    private MovieHelper mHelper;
    @Override
    public boolean onCreate() {
        mHelper = new MovieHelper(getContext());
        return true;
    }

    public static UriMatcher getUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MovieContract.AUTHORITY, MovieContract.PATH, MOVIE);
        uriMatcher.addURI(MovieContract.AUTHORITY, MovieContract.PATH + "/#", MOVIE_WITH_ID);
        return uriMatcher;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int match = mUriMatcher.match(uri);
        Uri returnUri;
        switch (match){
            case MOVIE:
                long newId = db.insert(MovieContract.TABLE, null, contentValues);
                if (newId > 0){
                    returnUri = ContentUris.withAppendedId(MovieContract.MovieEntry.CONTENT_URI, newId);
                }
                else {
                    throw new SQLException("Gagal menambah data ke " + uri);
                }
                break;
            default:
                throw  new  UnsupportedOperationException("Unknown uri : " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        int match = mUriMatcher.match(uri);
        Cursor returnCursor;
        switch (match){
            case MOVIE:
                returnCursor = db.query(
                        MovieContract.TABLE,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null);
                break;
            default:throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }



    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int match = mUriMatcher.match(uri);
        switch (match){
            case MOVIE_WITH_ID:
                break;
            case  MOVIE:
                break;
        }
        return 0;
    }


}
