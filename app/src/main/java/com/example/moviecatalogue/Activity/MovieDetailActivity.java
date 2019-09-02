package com.example.moviecatalogue.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogue.Movie.Movie;
import com.example.moviecatalogue.R;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView imgMovie = findViewById(R.id.imgMovie);
        TextView tvTitle = findViewById(R.id.tv_JudulDetail);
        TextView tvRelease = findViewById(R.id.tv_TglRilisDetail);
        TextView tvDuration = findViewById(R.id.tv_DurasiDetail);
        TextView tvOverview = findViewById(R.id.tv_Overview);

        Movie movie = getIntent().getParcelableExtra("movie");

        imgMovie.setImageResource(movie.getPic());
        tvTitle.setText(movie.getTitle());
        tvRelease.setText(movie.getRelease_date());
        tvDuration.setText(movie.getDuration());
        tvOverview.setText(movie.getOverview());
    }
}
