package com.example.moviecatalogue.tvshow;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class TvShowLoader extends AsyncTaskLoader<ArrayList<TvShow>> {

    private ArrayList<TvShow> shows;
    private boolean result = false;

    private String tvshow;

    public TvShowLoader(Context cont, String tvshow) {
        super(cont);

        onContentChanged();
        this.tvshow = tvshow;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (result)
            deliverResult(shows);
    }

    @Override
    public void deliverResult(final ArrayList<TvShow> data) {
        shows = data;
        result = true;
        super.deliverResult(data);
    }

    public static final String API_KEY = "5f793d033ea33558e13b3664b3eadca9";

    @Override
    public ArrayList<TvShow> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<TvShow> showItem = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String now = dateFormat.format(Calendar.getInstance().getTime());
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&primary_release_date.gte=" +
                "" + now + "&primary_release_date.lte=" + now + "";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject shows = list.getJSONObject(i);
                        TvShow showItems = new TvShow(shows);
                        showItem.add(showItems);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return showItem;
    }
}
