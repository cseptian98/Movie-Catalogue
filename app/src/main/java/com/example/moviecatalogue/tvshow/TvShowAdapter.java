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

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.activity.TvShowDetailActivity;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TvShow> tvShows;

    public void setTvShow(ArrayList<TvShow> items) {
        tvShows.clear();
        tvShows.addAll(items);
        notifyDataSetChanged();
    }

    public TvShowAdapter(ArrayList<TvShow> showData, Context context) {
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
                .load(imgUrl + tvShows.get(pos).getImgShow())
                .into(holder.imgShow);

        holder.tvTitleShow.setText(tvShows.get(pos).getTitleShow());
        holder.tvReleaseShow.setText(tvShows.get(pos).getReleaseShow());
        /*String mReleaseDate = tvShows.get(pos).getReleaseShow();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(mReleaseDate);

            SimpleDateFormat nDateFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            String nReleaseDate = nDateFormat.format(date);
            holder.tvReleaseShow.setText(nReleaseDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        holder.tvRateShow.setText(tvShows.get(pos).getRateShow());

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
        TextView tvTitleShow, tvReleaseShow, tvRateShow;
        RelativeLayout itemShow;

        ViewHolder(View itemView) {
            super(itemView);
            imgShow = itemView.findViewById(R.id.pic_movie);
            tvTitleShow = itemView.findViewById(R.id.tv_Title);
            tvReleaseShow = itemView.findViewById(R.id.tv_Date);
            tvRateShow = itemView.findViewById(R.id.tvRate);
            itemShow = itemView.findViewById(R.id.rvMovie);
        }
    }
}
