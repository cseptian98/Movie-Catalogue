package com.example.moviecatalogue.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.TvShow.TvShow;

public class TvShowDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView imgTvShow = findViewById(R.id.imgMovie);
        TextView tvJudul = findViewById(R.id.tv_JudulDetail);
        TextView tvRilis= findViewById(R.id.tv_TglRilisDetail);
        TextView tvDurasi = findViewById(R.id.tv_DurasiDetail);
        TextView tvDetail = findViewById(R.id.tv_Overview);

        TvShow tvShow = getIntent().getParcelableExtra("tvShow");

        imgTvShow.setImageResource(tvShow.getPoster());
        tvJudul.setText(tvShow.getJudul());
        tvRilis.setText(tvShow.getRilis());
        tvDurasi.setText(tvShow.getDurasi());
        tvDetail.setText(tvShow.getOverview());
    }
}