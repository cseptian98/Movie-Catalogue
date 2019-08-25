package com.example.moviecatalogue.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.TvShow.TvShow;

public class TvShowDetailActivity extends AppCompatActivity {

    public static final String Extra_Poster = "Extra_Poster";
    public static final String Extra_Judul = "Extra_Judul";
    public static final String Extra_Rilis = "Extra_Rilis";
    public static final String Extra_Durasi = "Extra_Durasi";
    public static final String Extra_Detail = "Extra_Detail";

    private Context context;
    private TvShow shows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView imgTvShow = findViewById(R.id.imgMovie);
        TextView tvJudul = findViewById(R.id.tv_JudulDetail);
        TextView tvRilis= findViewById(R.id.tv_TglRilisDetail);
        TextView tvDurasi = findViewById(R.id.tv_DurasiDetail);
        TextView tvDetail = findViewById(R.id.tv_Overview);

        final String picShow = getIntent().getStringExtra(Extra_Poster);
        final String judulShow = getIntent().getStringExtra(Extra_Judul);
        final String rilisShow = getIntent().getStringExtra(Extra_Rilis);
        final String durasiShow = getIntent().getStringExtra(Extra_Durasi);
        final String detailShow = getIntent().getStringExtra(Extra_Detail);

        tvJudul.setText(judulShow);
        tvRilis.setText(rilisShow);
        tvDurasi.setText(durasiShow);
        tvDetail.setText(detailShow);
    }
}