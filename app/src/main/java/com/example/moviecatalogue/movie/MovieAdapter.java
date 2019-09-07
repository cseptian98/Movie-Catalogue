package com.example.moviecatalogue.movie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.moviecatalogue.activity.MovieDetailActivity;
import com.example.moviecatalogue.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    public void setMovies(ArrayList<Movie> movies){
        movies.clear();
        movies.addAll(movies);
        notifyDataSetChanged();
    }

    public MovieAdapter(ArrayList<Movie> movieData, Context context){
        this.context = context;
        this.movies = movieData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, final int pos) {

        String imgUrl = "https://image.tmdb.org/t/p/w185/";

        Picasso.with(context)
                .load(imgUrl+movies.get(pos).getPic())
                .into(holder.imgMovie);

        holder.tvTitle.setText(movies.get(pos).getTitle());
        holder.tvRelease.setText(movies.get(pos).getRelease_date());
        holder.tvOverview.setText(movies.get(pos).getOverview());

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Movie item = movies.get(pos);

                Intent intent = new Intent(context, MovieDetailActivity.class);

                intent.putExtra(MovieDetailActivity.Extra_Id, item.getId());
                intent.putExtra(MovieDetailActivity.Extra_Image, item.getPic());
                intent.putExtra(MovieDetailActivity.Extra_Title, item.getTitle());
                intent.putExtra(MovieDetailActivity.Extra_Release, item.getRelease_date());
                intent.putExtra(MovieDetailActivity.Extra_Overview, item.getOverview());
                intent.putExtra(MovieDetailActivity.Extra_Rate, item.getRate());
                intent.putExtra(MovieDetailActivity.Extra_Backdrop, item.getBackdrop());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMovie;
        TextView tvTitle, tvRelease, tvOverview;
        RelativeLayout item;

        ViewHolder(View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.pic_movie);
            tvTitle = itemView.findViewById(R.id.tv_Title);
            tvRelease = itemView.findViewById(R.id.tv_Date);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            item = itemView.findViewById(R.id.rvMovie);
        }
    }
}