package com.example.favoritemovie.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.favoritemovie.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.favoritemovie.util.DataContract.MovieColumns.*;
import static com.example.favoritemovie.util.DataContract.getColumnString;

public class FavoriteMovieAdapter extends CursorAdapter {
    private Cursor listMovie;
    private Context context;

    public FavoriteMovieAdapter(Context context, Cursor c, boolean autoRequery){
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_list, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        ImageView imgMovie;
        TextView tvTitle, tvRate, tvRelease;

        if(cursor != null){
            imgMovie = view.findViewById(R.id.pic_movie);
            tvTitle = view.findViewById(R.id.tv_Title);
            tvRate = view.findViewById(R.id.tvRate);
            tvRelease = view.findViewById(R.id.tv_Date);

            String imgUrl = "http://image.tmdb.org/t/p/w185/";
            Picasso.with(context).load(imgUrl+getColumnString(cursor,Poster)).into(imgMovie);

            tvTitle.setText(getColumnString(cursor,Title));
            tvRate.setText(getColumnString(cursor,Rate));

            String mReleaseDate = getColumnString(cursor,Release_date);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(mReleaseDate);

                SimpleDateFormat nDateFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
                String nReleaseDate = nDateFormat.format(date);
                tvRelease.setText(nReleaseDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }
}
