package com.example.moviecatalogue.tvshow;

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

import com.example.moviecatalogue.activity.TvShowDetailActivity;
import com.example.moviecatalogue.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TvShow> tvShows;

    public void setTvShow(ArrayList<TvShow> tvShows){
        tvShows.clear();
        tvShows.addAll(tvShows);
        notifyDataSetChanged();
    }

    public TvShowAdapter(ArrayList<TvShow> showData, Context context){
        this.context = context;
        this.tvShows = showData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.ViewHolder holder, final int pos) {

        String imgUrl = "https://image.tmdb.org/t/p/w185/";

        Picasso.with(context)
                .load(imgUrl+tvShows.get(pos).getImgShow())
                .into(holder.imgShow);

        holder.tvTitleShow.setText(tvShows.get(pos).getTitleShow());
        holder.tvOverviewShow.setText(tvShows.get(pos).getOverviewShow());
        holder.tvReleaseShow.setText(tvShows.get(pos).getReleaseShow());

        holder.itemShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TvShow item = tvShows.get(pos);

                Intent intent = new Intent(context, TvShowDetailActivity.class);

                intent.putExtra(TvShowDetailActivity.Extra_Id, item.getId());
                intent.putExtra(TvShowDetailActivity.Extra_Image, item.getImgShow());
                intent.putExtra(TvShowDetailActivity.Extra_Title, item.getTitleShow());
                intent.putExtra(TvShowDetailActivity.Extra_Release, item.getReleaseShow());
                intent.putExtra(TvShowDetailActivity.Extra_Overview, item.getOverviewShow());
                intent.putExtra(TvShowDetailActivity.Extra_Rate, item.getRateShow());
                intent.putExtra(TvShowDetailActivity.Extra_Backdrop, item.getBackShow());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgShow;
        TextView tvTitleShow, tvReleaseShow, tvOverviewShow;
        RelativeLayout itemShow;

        ViewHolder(View itemView) {
            super(itemView);
            imgShow = itemView.findViewById(R.id.pic_movie);
            tvTitleShow = itemView.findViewById(R.id.tv_Title);
            tvReleaseShow = itemView.findViewById(R.id.tv_Date);
            tvOverviewShow = itemView.findViewById(R.id.tvOverview);
            itemShow = itemView.findViewById(R.id.rvMovie);
        }
    }
}
