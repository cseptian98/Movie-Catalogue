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
import com.example.moviecatalogue.tvshow.TvShow;
import com.example.moviecatalogue.tvshow.TvShowAdapter;
import com.example.moviecatalogue.viewmodel.ShowViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchShowFragment extends Fragment {

    RecyclerView listView;
    TvShowAdapter adapter;
    SearchView keyword;

    private ArrayList<TvShow> listShow;
    private ShowViewModel showViewModel;

    public SearchShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        keyword = view.findViewById(R.id.keyword);
        listView = view.findViewById(R.id.listMovie);

        showViewModel = ViewModelProviders.of(this).get(ShowViewModel.class);
        showViewModel.getShow().observe(this, getShow);

        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listShow = new ArrayList<>();

        adapter = new TvShowAdapter(listShow, getActivity().getApplicationContext());
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        keyword.onActionViewExpanded();
        keyword.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String search = keyword.getQuery().toString();

                if (TextUtils.isEmpty(search)) return false;

                showViewModel.setListShow(search);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String search = keyword.getQuery().toString();

                if (TextUtils.isEmpty(search)) return false;

                showViewModel.setListShow(search);
                return false;
            }
        });

        return view;
    }

    private Observer<ArrayList<TvShow>> getShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(ArrayList<TvShow> showItems) {
            if (showItems != null) {
                adapter.setTvShow(showItems);
            }
        }
    };
}
