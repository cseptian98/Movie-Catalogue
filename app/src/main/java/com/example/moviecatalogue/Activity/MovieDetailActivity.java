package com.example.moviecatalogue.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogue.Movie.Movie;
import com.example.moviecatalogue.R;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

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

        ImageView imgMovie = findViewById(R.id.imgMovie);
        TextView tvTitle = findViewById(R.id.tvTitleDetail);
        TextView tvRelease = findViewById(R.id.tvReleaseDetail);
        TextView tvRate = findViewById(R.id.tvRateDetail);
        TextView tvOverview = findViewById(R.id.tvOverview);
        ImageView backMovie = findViewById(R.id.backMovie);

        final int movieId = getIntent().getIntExtra(Extra_Id,0);
        final String picMovie = getIntent().getStringExtra(Extra_Image);
        final String titleMovie = getIntent().getStringExtra(Extra_Title);
        final String releaseMovie = getIntent().getStringExtra(Extra_Release);
        final String rateMovie = getIntent().getStringExtra(Extra_Rate);
        final String overviewMovie = getIntent().getStringExtra(Extra_Overview);
        final String backdropMovie = getIntent().getStringExtra(Extra_Backdrop);

        Picasso.with(context).load("https://image.tmdb.org/t/p/w300/"+picMovie).into(imgMovie);
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500/"+backdropMovie).into(backMovie);

        tvTitle.setText(titleMovie);
        tvRelease.setText(releaseMovie);
        tvRate.setText(rateMovie);
        tvOverview.setText(overviewMovie);
    }
}
