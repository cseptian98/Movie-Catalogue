package com.example.moviecatalogue.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.movie.Movie;
import com.example.moviecatalogue.supports.MovieHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StackView implements RemoteViewsService.RemoteViewsFactory {

    public ArrayList<Movie> listMovie;
    private Context newContext;
    private int nAppWidget;
    private MovieHelper helper;

    public StackView(Context context, Intent intent) {
        newContext = context;
        nAppWidget = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        helper = new MovieHelper(newContext);
        helper.open();
        listMovie = new ArrayList<>();
        listMovie.addAll(helper.getData());
        helper.close();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return listMovie.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(newContext.getPackageName(), R.layout.widget_item);
        Bitmap bitmap = null;
        try {
            bitmap = Picasso.with(newContext).load("https://image.tmdb.org/t/p/w185/" + listMovie.get(position).getPic()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("log", listMovie.get(position).getPic());
        rv.setImageViewBitmap(R.id.imageView, bitmap);
        rv.setTextViewText(R.id.tv_title, listMovie.get(position).getTitle());

        Bundle bundle = new Bundle();
        bundle.putInt(FavWidget.Extra_Item, position);
        Intent fillIntent = new Intent();
        fillIntent.putExtras(bundle);
        rv.setOnClickFillInIntent(R.id.imageView, fillIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
