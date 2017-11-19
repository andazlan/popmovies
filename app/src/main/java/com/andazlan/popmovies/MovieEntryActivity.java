package com.andazlan.popmovies;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andazlan.popmovies.data.MovieContract;
import com.andazlan.popmovies.data.MovieHelper;

public class MovieEntryActivity extends AppCompatActivity {
    private EditText nama, rating;
    private Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_entry);

        nama = findViewById(R.id.edt_nama);
        rating = findViewById(R.id.edt_rating);
        simpan = findViewById(R.id.btn_simpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(MovieContract.MovieEntry.COLUMN_MOVIE_NAME,
                        nama.getText().toString());
                values.put(MovieContract.MovieEntry.COLUMN_MOVIE_RATING,
                        Integer.parseInt(rating.getText().toString()));

                Uri uri = getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, values);

                if (uri != null){
                    Toast.makeText(MovieEntryActivity.this,
                            "Data berhasil ditambahkan di : " + uri.toString(), Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(MovieEntryActivity.this,
                            "Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
                }


                /* TODO : W3.4 Untuk melakukan sesuatu di database, dibutuhkan helper object.
                // Buat database helper
                MovieHelper helper = new MovieHelper(this);

                // TODO : W3.5 Dengan bantuan helper, database sudah bisa kita akses.
                SQLiteDatabase db = mHelper.getWritableDatabase();


                //return long => id > 0, < 0/-1 => failed

                // TODO: W3.6 Setelah mengakses database, insert, update, delete, dan select sudah bisa kita lakukan
                // Entry yang ingin kita masukkan kedalam database menggunakan content values
                // db.insert membutuhkan 3 parameter, nama tabel, columnHack, dan values

                // perintah insert mengembalikan sebuah nilai berupa long dimana nilai ini adalah id data baru yang kita
                // masukkan ke database

                // jika hasilnya -1, maka data gagal dimasukkan
                long newId = db.insert(MovieContract.TABLE, null, values);
                if (newId > 0){
                    Toast.makeText(MovieEntryActivity.this,
                            "Data tersimpan", Toast.LENGTH_SHORT).show();
                    clearAll();
                }
                */
            }
        });
    }

    private void clearAll() {
        nama.setText("");
        rating.setText("");
    }
}
