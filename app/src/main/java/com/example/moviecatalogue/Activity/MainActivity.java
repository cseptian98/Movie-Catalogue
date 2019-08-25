package com.example.moviecatalogue.Activity;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.moviecatalogue.Movie.Movie;
import com.example.moviecatalogue.Movie.MovieAdapter;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.TvShow.TvShow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] dataTitle;
    private String[] dataRelease;
    private String[] dataDuration;
    private String[] dataOverview;

    private String[] dataJudulShow;
    private String[] dataRilisShow;
    private String[] dataDurasiShow;
    private String[] dataDetailShow;

    private TypedArray dataPic;
    private TypedArray dataImgShow;

    private RecyclerView rvMovie;
    private ArrayList<Movie> movies;
    private ArrayList<TvShow> shows;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepare();
        addMovie();
        //addTvShow();
        rvMovie = findViewById(R.id.movie_list);
        rvMovie.setHasFixedSize(true);

        rvMovie.setLayoutManager(new LinearLayoutManager(this));
        MovieAdapter movieAdapter = new MovieAdapter(movies, this);
        rvMovie.setAdapter(movieAdapter);

    }

    private void prepare(){
        dataTitle = getResources().getStringArray(R.array.data_title);
        dataRelease = getResources().getStringArray(R.array.release_date);
        dataPic = getResources().obtainTypedArray(R.array.pic);
        dataDuration = getResources().getStringArray(R.array.duration);
        dataOverview = getResources().getStringArray(R.array.overview);

        dataJudulShow = getResources().getStringArray(R.array.titleTvShow);
        dataImgShow = getResources().obtainTypedArray(R.array.picShow);
        dataRilisShow = getResources().getStringArray(R.array.rilisShow);
    }

    private void addMovie(){
        movies = new ArrayList<>();

        for(int i = 0; i < dataTitle.length; i++){
            Movie movie = new Movie();
            movie.setPic(dataPic.getResourceId(i, -1));
            movie.setTitle(dataTitle[i]);
            movie.setRelease_date(dataRelease[i]);
            movie.setDuration(dataDuration[i]);
            movie.setOverview(dataOverview[i]);
            movies.add(movie);
        }
    }

    private void addTvShow(){
         shows = new ArrayList<>();

        for(int i = 0; i < dataJudulShow.length; i++){
            TvShow show = new TvShow();
            show.setPoster(dataImgShow.getResourceId(i, -1));
            show.setJudul(dataJudulShow[i]);
            show.setRilis(dataRilisShow[i]);
            //show.setDurasi(dataDurasiShow[i]);
            //show.setOverview(dataDetailShow[i]);
            shows.add(show);
        }
    }
}

