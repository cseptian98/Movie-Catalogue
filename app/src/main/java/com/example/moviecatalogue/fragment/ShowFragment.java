package com.example.moviecatalogue.fragment;


import android.app.ProgressDialog;
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
import com.example.moviecatalogue.tvshow.TvShow;
import com.example.moviecatalogue.tvshow.TvShowAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment {

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private View view;
    private ArrayList<TvShow> shows;

    private static final String API_URL = "https://api.themoviedb.org/3/tv/top_rated?api_key=5f793d033ea33558e13b3664b3eadca9&language=en-US";

    public ShowFragment() {
        // Required empty public constructor
    }

    public static ShowFragment newInstance() {
        return new ShowFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show, container, false);

        rv = view.findViewById(R.id.recycle_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        shows = new ArrayList<>();

        loadTVShow();

        return view;
    }

    public void loadTVShow() {
        final ProgressDialog dialogShow = new ProgressDialog(getActivity());

        dialogShow.setMessage(getResources().getString(R.string.load));
        dialogShow.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                dialogShow.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++) {

                        TvShow show = new TvShow(array.getJSONObject(i));

                        JSONObject data = array.getJSONObject(i);
                        show.setTitleShow(data.getString("name"));
                        show.setOverviewShow(data.getString("overview"));
                        show.setReleaseShow(data.getString("first_air_date"));
                        show.setImgShow(data.getString("poster_path"));

                        shows.add(show);

                    }

                    adapter = new TvShowAdapter(shows, getActivity());
                    rv.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                loadTVShow();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
