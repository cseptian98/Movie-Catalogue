package com.example.moviecatalogue.Fragment;


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
public class ShowFragment extends Fragment {

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private View view;
    private ArrayList<Movie> movies;

    public ShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_show, container, false);

        rv = view.findViewById(R.id.recycle_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MovieAdapter(movies,getActivity());
        rv.setAdapter(adapter);

        return view;
    }

}
