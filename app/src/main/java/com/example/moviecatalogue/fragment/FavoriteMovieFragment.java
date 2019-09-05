package com.example.moviecatalogue.fragment;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.movie.FavoriteMovieAdapter;

import static com.example.moviecatalogue.util.MovieContract.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment {

    private RecyclerView rv;
    private FavoriteMovieAdapter adapter;
    private View view;
    private Cursor listMovie;

    private static final String API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=5f793d033ea33558e13b3664b3eadca9&language=en-US";

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie, container, false);

        rv = view.findViewById(R.id.recycle_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new FavoriteMovieAdapter(listMovie, getContext());
        adapter.setListMovie(listMovie);
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
            return getActivity().getApplicationContext().getContentResolver().query(CONTENT_URI,null,null,null,null);
        }

        @Override
        public void onPostExecute(Cursor movie) {
            super.onPostExecute(movie);

            listMovie = movie;
            adapter.setListMovie(listMovie);
            adapter.notifyDataSetChanged();

            if (listMovie.getCount() == 0){
                showSnackbarMessage(getResources().getString(R.string.fav_movie_null));
            }
        }
    }

    private void showSnackbarMessage(String message){
        Snackbar.make(rv, message, Snackbar.LENGTH_SHORT).show();
    }
}
