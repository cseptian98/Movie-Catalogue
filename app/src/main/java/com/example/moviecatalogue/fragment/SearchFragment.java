package com.example.moviecatalogue.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
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
import com.example.moviecatalogue.movie.MovieLoader;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Movie>> {

    RecyclerView listView;
    MovieAdapter adapter;
    SearchView keyword;

    private ArrayList<Movie> listMovie;

    static final String Extra_Movie = "Extra_Movie";

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

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
            public boolean onQueryTextSubmit(String s) {
                String search = keyword.getQuery().toString();

                if(TextUtils.isEmpty(search)) return false;

                Bundle bundle = new Bundle();
                bundle.putString(Extra_Movie, search);
                getLoaderManager().restartLoader(0,bundle, SearchFragment.this);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String search = keyword.getQuery().toString();

                if(TextUtils.isEmpty(search)) return false;

                Bundle bundle = new Bundle();
                bundle.putString(Extra_Movie, search);
                getLoaderManager().restartLoader(0, bundle, SearchFragment.this);
                return false;
            }
        });

        String title = keyword.getQuery().toString();
        Bundle bundle = new Bundle();
        bundle.putString(Extra_Movie, title);

        getLoaderManager().initLoader(0, bundle, SearchFragment.this);

        return view;
    }

    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int id, Bundle args){
        String movie = "";
        if(args != null){
            movie = args.getString(Extra_Movie);
        }

        return new MovieLoader(getActivity(), movie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Movie>> loader, ArrayList<Movie> data) {
        adapter.setMovies(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Movie>> loader){
        adapter.setMovies(null);
    }

}
