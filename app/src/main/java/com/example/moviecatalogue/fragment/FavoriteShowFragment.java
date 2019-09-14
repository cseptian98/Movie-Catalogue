package com.example.moviecatalogue.fragment;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.tvshow.FavoriteShowAdapter;

import static com.example.moviecatalogue.util.DataContract.CONTENT_URI_SHOW;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteShowFragment extends Fragment {

    private RecyclerView rv;
    private FavoriteShowAdapter adapter;
    private View view;
    private Cursor listShow;

    private static final String API_URL = "https://api.themoviedb.org/3/tv/top_rated?api_key=5f793d033ea33558e13b3664b3eadca9&language=en-US";

    public FavoriteShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show, container, false);

        rv = view.findViewById(R.id.recycle_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new FavoriteShowAdapter(listShow, getContext());
        adapter.setListShow(listShow);
        rv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new loadData().execute();
    }

    public class loadData extends AsyncTask<Void, Void, Cursor> {
        @Override
        public void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public Cursor doInBackground(Void... voids) {
            return getActivity().getApplicationContext().getContentResolver().query(CONTENT_URI_SHOW, null, null, null, null);
        }

        @Override
        public void onPostExecute(Cursor show) {
            super.onPostExecute(show);

            listShow = show;
            adapter.setListShow(listShow);
            adapter.notifyDataSetChanged();
        }
    }
}
