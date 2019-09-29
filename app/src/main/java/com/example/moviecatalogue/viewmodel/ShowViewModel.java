package com.example.moviecatalogue.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.moviecatalogue.tvshow.TvShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ShowViewModel extends ViewModel {
    private static final String API_KEY = "5f793d033ea33558e13b3664b3eadca9";
    private MutableLiveData<ArrayList<TvShow>> listShow = new MutableLiveData<>();

    public void setListShow(final String show) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShow> showItem = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/tv?api_key=" + API_KEY + "&language=en-US&query=" + show + "";

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
                    listShow.postValue(showItem);
                } catch (Exception e) {
                    Log.d("exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<TvShow>> getShow() {
        return listShow;
    }
}
