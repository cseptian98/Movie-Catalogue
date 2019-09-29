package com.example.favoritemovie.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.favoritemovie.R;
import com.example.favoritemovie.items.Movie;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.favoritemovie.supports.DataContract.CONTENT_URI;

public class DetailActivity extends AppCompatActivity {

    public Context context;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imgPoster = findViewById(R.id.imgMovie);
        TextView tvTitle = findViewById(R.id.tvTitleDetail);
        TextView tvRelease = findViewById(R.id.tvReleaseDetail);
        TextView tvRate = findViewById(R.id.tvRateDetail);
        TextView tvOverview = findViewById(R.id.tvOverview);

        Uri uri = getIntent().getData();

        if (uri != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);

            if (cursor != null) {

                if (cursor.moveToFirst()) movie = new Movie(cursor);
                cursor.close();
            }
        }

        String moviePoster = movie.getPic();
        final String movieTitle = movie.getTitle();
        final String movieRelease = movie.getRelease_date();
        final String movieOverview = movie.getOverview();
        String movieRate = movie.getRate();

        Picasso.with(context).load("https://image.tmdb.org/t/p/w300/" + moviePoster).into(imgPoster);

        tvTitle.setText(movieTitle);
        tvOverview.setText(movieOverview);
        tvRate.setText(movieRate);

        String mReleaseDate = movieRelease;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(mReleaseDate);

            SimpleDateFormat nDateFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            String nReleaseDate = nDateFormat.format(date);
            tvRelease.setText(nReleaseDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        final int movieId = movie.getId();

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(movieId);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void showAlert(final int movieID) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(getResources().getString(R.string.delete));
        alertDialogBuilder
                .setMessage(getResources().getString(R.string.acc_delete_movie))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getContentResolver().delete(Uri.parse(CONTENT_URI + "/" + movieID), null, null);
                        finish();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
