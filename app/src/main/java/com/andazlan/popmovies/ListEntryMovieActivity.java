package com.andazlan.popmovies;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.andazlan.popmovies.data.MovieContract;
import com.andazlan.popmovies.data.MovieEntryAdapter;
import com.andazlan.popmovies.data.MovieHelper;

public class ListEntryMovieActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int MOVIES_LOADER = 100;
    private ListView listMovie;
    private MovieEntryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_entry_movie);

        listMovie = findViewById(R.id.list_entry_movie);

        //Cursor dataCollection = getAllDatas();

        mAdapter = new MovieEntryAdapter(this, null);
        listMovie.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(MOVIES_LOADER, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu_entry, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_tambah:
                Intent intent = new Intent(this, MovieEntryActivity.class);
                startActivity(intent);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO : we don't need it anymore, handled by loader
        //mAdapter.swapCursor(getAllDatas());
    }

    //TODO : we don't need it anymore, handled by loader
    /*
    public Cursor getAllDatas(){
        /*
        SQLiteDatabase db = mHelper.getReadableDatabase();

        ;

        Cursor cursor = db.query(
                MovieContract.TABLE,
                projection,
                null,
                null,
                null,
                null,
                null);


        String[] projection = {
                MovieContract.MovieEntry._ID,
                MovieContract.MovieEntry.COLUMN_MOVIE_NAME,
                MovieContract.MovieEntry.COLUMN_MOVIE_RATING
        };

        Cursor cursor = getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                projection, null, null, null);

        return cursor;
    }
    */

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                MovieContract.MovieEntry._ID,
                MovieContract.MovieEntry.COLUMN_MOVIE_NAME,
                MovieContract.MovieEntry.COLUMN_MOVIE_RATING
        };

        return new CursorLoader(this, MovieContract.MovieEntry.CONTENT_URI, projection,
                null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
