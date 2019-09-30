package com.example.moviecatalogue.fragment;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.movie.Movie;
import com.example.moviecatalogue.movie.MovieAdapter;
import com.example.moviecatalogue.viewmodel.MovieViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    RecyclerView rv;
    MovieAdapter adapter;
    View view;
    ArrayList<Movie> movies;
    MovieViewModel movieViewModel;

    private static final String API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=5f793d033ea33558e13b3664b3eadca9&language=en-US";

    public MovieFragment() {
        // Required empty public constructor
    }

    public static MovieFragment newInstance() {
        return new MovieFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movie, container, false);

        movieViewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        movieViewModel.getMovie().observe(this, getMovie);

        rv = view.findViewById(R.id.recycle_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        movies = new ArrayList<>();
        loadMovies();

        return view;
    }

    public void loadMovies() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());

        progressDialog.setMessage(getResources().getString(R.string.load));
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++) {

                        Movie movie = new Movie(array.getJSONObject(i));

                        JSONObject data = array.getJSONObject(i);
                        movie.setTitle(data.getString("title"));
                        movie.setOverview(data.getString("overview"));
                        movie.setRelease_date(data.getString("release_date"));
                        movie.setPic(data.getString("poster_path"));

                        movies.add(movie);

                    }

                    adapter = new MovieAdapter(movies, getActivity());
                    rv.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                loadMovies();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
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
