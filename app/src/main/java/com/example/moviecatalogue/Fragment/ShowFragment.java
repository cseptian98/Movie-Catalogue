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
import com.example.moviecatalogue.TvShow.TvShow;
import com.example.moviecatalogue.TvShow.TvShowAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment {

    private String[] dataJudulShow;
    private String[] dataRilisShow;
    private String[] dataDurasiShow;
    private String[] dataDetailShow;
    private TypedArray dataImgShow;

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private View view;
    private ArrayList<TvShow> shows;

    public ShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_show, container, false);
        prepare();
        addTvShow();

        rv = view.findViewById(R.id.recycle_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        shows = new ArrayList<>();
        adapter = new TvShowAdapter(shows,getActivity());
        rv.setAdapter(adapter);

        return view;
    }

    private void prepare(){
        dataJudulShow = getResources().getStringArray(R.array.titleTvShow);
        dataImgShow = getResources().obtainTypedArray(R.array.picShow);
        dataRilisShow = getResources().getStringArray(R.array.rilisShow);
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
