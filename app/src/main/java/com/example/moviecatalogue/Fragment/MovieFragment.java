package com.example.moviecatalogue.Fragment;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalogue.Movie.Movie;
import com.example.moviecatalogue.Movie.MovieAdapter;
import com.example.moviecatalogue.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private String[] dataTitle;
    private String[] dataRelease;
    private String[] dataDuration;
    private String[] dataOverview;

    private TypedArray dataPic;

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private View view;
    private ArrayList<Movie> movies;


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movie, container, false);
        addMovie();
        prepare();

        rv = view.findViewById(R.id.recycle_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        movies = new ArrayList<>();
        adapter = new MovieAdapter(movies, getActivity());
        rv.setAdapter(adapter);

        return view;
    }

    private void prepare() {
        dataTitle = getResources().getStringArray(R.array.data_title);
        dataRelease = getResources().getStringArray(R.array.release_date);
        dataPic = getResources().obtainTypedArray(R.array.pic);
        dataDuration = getResources().getStringArray(R.array.duration);
        dataOverview = getResources().getStringArray(R.array.overview);
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
}
