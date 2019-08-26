package com.example.moviecatalogue.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogue.R;

public class MovieDetailActivity extends AppCompatActivity {

    int picMovie;
    String titleMovie, rilisMovie, durationMovie, ovMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView imgMovie = findViewById(R.id.imgMovie);
        TextView tvTitle = findViewById(R.id.tv_JudulDetail);
        TextView tvRelease = findViewById(R.id.tv_TglRilisDetail);
        TextView tvDuration = findViewById(R.id.tv_DurasiDetail);
        TextView tvOverview = findViewById(R.id.tv_Overview);

        picMovie = getIntent().getIntExtra("imgMovie", 999);
        titleMovie = getIntent().getStringExtra("titleMovie");
        rilisMovie = getIntent().getStringExtra("rilisMovie");
        durationMovie = getIntent().getStringExtra("durationMovie");
        ovMovie = getIntent().getStringExtra("ovMovie");

        Glide.with(this)
                .load(picMovie)
                .apply(new RequestOptions().override(365,485))
                .into(imgMovie);
        Log.d("aaaa","url"+picMovie);

        tvTitle.setText(titleMovie);
        tvRelease.setText(rilisMovie);
        tvDuration.setText(durationMovie);
        tvOverview.setText(ovMovie);
    }
}
