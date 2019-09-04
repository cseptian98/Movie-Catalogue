package com.example.moviecatalogue.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviecatalogue.R;
import com.squareup.picasso.Picasso;

public class TvShowDetailActivity extends AppCompatActivity {

    public static String Extra_Id = "Extra_Id";
    public static String Extra_Image = "Extra_Image";
    public static String Extra_Title = "Extra_Title";
    public static String Extra_Release = "Extra_Release";
    public static String Extra_Overview = "Extra_Overview";
    public static String Extra_Rate = "Extra_Rate";
    public static String Extra_Backdrop = "Extra_Backdrop";

    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView imgTvShow = findViewById(R.id.imgMovie);
        TextView tvTitle = findViewById(R.id.tvTitleDetail);
        TextView tvRelease= findViewById(R.id.tvReleaseDetail);
        TextView tvRate = findViewById(R.id.tvRateDetail);
        TextView tvOverview = findViewById(R.id.tvOverview);
        ImageView backShow = findViewById(R.id.backMovie);

        final String picShow = getIntent().getStringExtra(Extra_Image);
        final String titleShow = getIntent().getStringExtra(Extra_Title);
        final String releaseShow = getIntent().getStringExtra(Extra_Release);
        final String rateShow = getIntent().getStringExtra(Extra_Rate);
        final String overviewShow = getIntent().getStringExtra(Extra_Overview);
        final String backdropShow = getIntent().getStringExtra(Extra_Backdrop);

        Picasso.with(context).load("https://image.tmdb.org/t/p/w300/"+picShow).into(imgTvShow);
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500/"+backdropShow).into(backShow);

        tvTitle.setText(titleShow);
        tvRelease.setText(releaseShow);
        tvRate.setText(rateShow);
        tvOverview.setText(overviewShow);
    }
}