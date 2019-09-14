package com.example.moviecatalogue.movie;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieLoader extends AsyncTaskLoader<ArrayList<Movie>> {

    private ArrayList<Movie> movieData;
    private boolean mHasResult = false;

    private String movie;

    public MovieLoader(Context context, String movie){
        super(context);

        onContentChanged();
        this.movie = movie;
    }

    @Override
    protected void onStartLoading(){
        if(takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(movieData);
    }

    @Override
    public void deliverResult(final ArrayList<Movie> data){
        movieData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    public void onReset() {
        super.onReset();
        onStopLoading();
        if(mHasResult){
            movieData = null;
            mHasResult = false;
        }
    }

    public static final String API_KEY = "5f793d033ea33558e13b3664b3eadca9";

    @Override
    public ArrayList<Movie> loadInBackground(){
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<Movie> movieItem = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+API_KEY+"&language=en-US&query="+movie;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movies = list.getJSONObject(i);
                        Movie movieItems = new Movie(movies);
                        movieItem.add(movieItems);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return movieItem;
    }
}