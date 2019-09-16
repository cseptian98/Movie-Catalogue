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
import com.example.moviecatalogue.tvshow.TvShow;
import com.example.moviecatalogue.tvshow.TvShowAdapter;
import com.example.moviecatalogue.tvshow.TvShowLoader;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchShowFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<TvShow>> {

    RecyclerView listView;
    TvShowAdapter adapter;
    SearchView keyword;

    private ArrayList<TvShow> listShow;

    static final String Extra_Show = "Extra_Show";


    public SearchShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        keyword = view.findViewById(R.id.keyword);
        listView = view.findViewById(R.id.listMovie);

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

                if(TextUtils.isEmpty(search)) return false;

                Bundle bundle = new Bundle();
                bundle.putString(Extra_Show, search);
                getLoaderManager().restartLoader(0,bundle, SearchShowFragment.this);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String search = keyword.getQuery().toString();

                if(TextUtils.isEmpty(search)) return false;

                Bundle bundle = new Bundle();
                bundle.putString(Extra_Show, search);
                getLoaderManager().restartLoader(0, bundle, SearchShowFragment.this);
                return false;
            }
        });
        String title = keyword.getQuery().toString();
        Bundle bundle = new Bundle();
        bundle.putString(Extra_Show, title);

        getLoaderManager().initLoader(0, bundle, SearchShowFragment.this);

        return view;
    }

    @Override
    public Loader<ArrayList<TvShow>> onCreateLoader(int id, Bundle args){
        String shows = "";
        if(args != null){
            shows = args.getString(Extra_Show);
        }

        return new TvShowLoader(getActivity(), shows);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<TvShow>> loader, ArrayList<TvShow> data) {
        adapter.setTvShow(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<TvShow>> loader){
        adapter.setTvShow(null);
    }
}
