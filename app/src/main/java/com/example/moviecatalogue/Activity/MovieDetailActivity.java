package com.example.moviecatalogue.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviecatalogue.R;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String Extra_Image = "Extra_Image";
    public static final String Extra_Title = "Extra_Title";
    public static final String Extra_Release = "Extra_Release";
    public static final String Extra_Duration = "Extra_Duration";
    public static final String Extra_Overview = "Extra_Overview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView imgMovie = findViewById(R.id.imgMovie);
        TextView tvTitle = findViewById(R.id.tv_JudulDetail);
        TextView tvRelease = findViewById(R.id.tv_TglRilisDetail);
        TextView tvDuration = findViewById(R.id.tv_DurasiDetail);
        TextView tvOverview = findViewById(R.id.tv_Overview);

        final String picMovie = getIntent().getStringExtra(Extra_Image);
        final String titleMovie = getIntent().getStringExtra(Extra_Title);
        final String releaseMovie = getIntent().getStringExtra(Extra_Release);
        final String durationMovie = getIntent().getStringExtra(Extra_Duration);
        final String overviewMovie = getIntent().getStringExtra(Extra_Overview);

        Glide.with(this)
                .load(picMovie)
                .into(imgMovie);
        //Log.d("aaaa","url"+picMovie);

        tvTitle.setText(titleMovie);
        tvRelease.setText(releaseMovie);
        tvDuration.setText(durationMovie);
        tvOverview.setText(overviewMovie);
    }
}
