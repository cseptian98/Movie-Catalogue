package com.example.moviecatalogue.movie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.activity.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder> {
    private Cursor listMovie;
    private Context context;

    public FavoriteMovieAdapter(Cursor listMovie, Context context) {
        this.context = context;
        this.listMovie = listMovie;
    }

    public void setListMovie(Cursor listMovie) {
        this.listMovie = listMovie;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int pos) {

        String imgUrl = "https://image.tmdb.org/t/p/w185/";

        final Movie movies = getItem(pos);

        Picasso.with(context)
                .load(imgUrl + movies.getPic())
                .into(holder.imgMovie);

        holder.tvTitle.setText(movies.getTitle());
        String mReleaseDate = movies.getRelease_date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(mReleaseDate);

            SimpleDateFormat nDateFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            String nReleaseDate = nDateFormat.format(date);
            holder.tvRelease.setText(nReleaseDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvRate.setText(movies.getRate());

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MovieDetailActivity.class);

                intent.putExtra(MovieDetailActivity.Extra_Id, movies.getId());
                intent.putExtra(MovieDetailActivity.Extra_Image, movies.getPic());
                intent.putExtra(MovieDetailActivity.Extra_Title, movies.getTitle());
                intent.putExtra(MovieDetailActivity.Extra_Release, movies.getRelease_date());
                intent.putExtra(MovieDetailActivity.Extra_Overview, movies.getOverview());
                intent.putExtra(MovieDetailActivity.Extra_Rate, movies.getRate());
                intent.putExtra(MovieDetailActivity.Extra_Backdrop, movies.getBackdrop());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listMovie == null) return 0;
        return listMovie.getCount();
    }

    private Movie getItem(int position) {
        if (!listMovie.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new Movie(listMovie);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMovie;
        TextView tvTitle, tvRelease, tvRate;
        RelativeLayout item;

        ViewHolder(View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.pic_movie);
            tvTitle = itemView.findViewById(R.id.tv_Title);
            tvRelease = itemView.findViewById(R.id.tv_Date);
            tvRate = itemView.findViewById(R.id.tvRate);
            item = itemView.findViewById(R.id.rvMovie);
        }
    }
}
