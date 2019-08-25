package com.example.moviecatalogue.TvShow;

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

import com.example.moviecatalogue.Activity.MovieDetailActivity;
import com.example.moviecatalogue.Activity.TvShowDetailActivity;
import com.example.moviecatalogue.R;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TvShow> tvShows;

    public void setTvShow(ArrayList<TvShow> tvShows){
        this.tvShows = tvShows;
    }

    public TvShowAdapter(ArrayList<TvShow> showData, Context context){
        this.context = context;
        this.tvShows = showData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.ViewHolder holder, final int pos) {

        holder.imgShow.setImageResource(tvShows.get(pos).getPoster());
        holder.tvJudulShow.setText(tvShows.get(pos).getJudul());
        holder.tvOverview.setText(tvShows.get(pos).getOverview());
        holder.tvRilisShow.setText(tvShows.get(pos).getRilis());

        holder.itemShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TvShow item = tvShows.get(pos);

                Intent intent = new Intent(v.getContext(), TvShowDetailActivity.class);

                intent.putExtra(TvShowDetailActivity.Extra_Poster, item.getPoster());
                intent.putExtra(TvShowDetailActivity.Extra_Judul, item.getJudul());
                intent.putExtra(TvShowDetailActivity.Extra_Rilis, item.getRilis());
                intent.putExtra(TvShowDetailActivity.Extra_Durasi, item.getDurasi());
                intent.putExtra(TvShowDetailActivity.Extra_Detail, item.getOverview());

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgShow;
        TextView tvJudulShow, tvRilisShow, tvOverview;
        RelativeLayout itemShow;

        ViewHolder(View itemView) {
            super(itemView);
            imgShow = itemView.findViewById(R.id.pic_movie);
            tvJudulShow = itemView.findViewById(R.id.tv_Title);
            tvRilisShow = itemView.findViewById(R.id.tv_Date);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            itemShow = itemView.findViewById(R.id.rvMovie);
        }
    }
}
