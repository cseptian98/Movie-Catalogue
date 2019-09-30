package com.example.moviecatalogue.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.movie.Movie;
import com.example.moviecatalogue.movie.MovieAdapter;
import com.example.moviecatalogue.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class SearchMovieFragment extends Fragment {

    RecyclerView listView;
    MovieAdapter adapter;
    SearchView keyword;

    private ArrayList<Movie> listMovie;
    private MovieViewModel movieViewModel;

    public SearchMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        movieViewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        movieViewModel.getMovie().observe(this, getMovie);

        keyword = view.findViewById(R.id.keyword);
        listView = view.findViewById(R.id.listMovie);

        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listMovie = new ArrayList<>();

        adapter = new MovieAdapter(listMovie, getActivity().getApplicationContext());
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        keyword.onActionViewExpanded();
        keyword.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String search = keyword.getQuery().toString();

                if (TextUtils.isEmpty(search)) return false;

                movieViewModel.setListMovie(search);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String search = keyword.getQuery().toString();

                if (TextUtils.isEmpty(search)) return false;

                movieViewModel.setListMovie(search);
                return false;
            }
        });
        return view;
    }

    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movieItems) {
            if (movieItems != null) {
                adapter.setMovies(movieItems);
            }
        }
    };
}
