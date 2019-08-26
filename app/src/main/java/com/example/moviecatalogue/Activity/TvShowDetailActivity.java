package com.example.moviecatalogue.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.TvShow.TvShow;

public class TvShowDetailActivity extends AppCompatActivity {

    int picShow;
    String titleShow, rilisShow, durationShow, ovShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView imgTvShow = findViewById(R.id.imgMovie);
        TextView tvJudul = findViewById(R.id.tv_JudulDetail);
        TextView tvRilis= findViewById(R.id.tv_TglRilisDetail);
        TextView tvDurasi = findViewById(R.id.tv_DurasiDetail);
        TextView tvDetail = findViewById(R.id.tv_Overview);

        picShow = getIntent().getIntExtra("imgShow", 999);
        titleShow = getIntent().getStringExtra("titleShow");
        rilisShow = getIntent().getStringExtra("rilisShow");
        durationShow = getIntent().getStringExtra("durationShow");
        ovShow = getIntent().getStringExtra("ovShow");

        Glide.with(this)
                .load(picShow)
                .into(imgTvShow);
        Log.d("aaaa","url"+picShow);

        tvJudul.setText(titleShow);
        tvRilis.setText(rilisShow);
        tvDurasi.setText(durationShow);
        tvDetail.setText(ovShow);
    }
}