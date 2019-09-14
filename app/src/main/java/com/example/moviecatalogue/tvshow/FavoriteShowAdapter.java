package com.example.moviecatalogue.tvshow;

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
import com.example.moviecatalogue.activity.TvShowDetailActivity;
import com.squareup.picasso.Picasso;

public class FavoriteShowAdapter extends RecyclerView.Adapter<FavoriteShowAdapter.ViewHolder> {
    private Cursor listShow;
    private Context context;

    public FavoriteShowAdapter(Cursor listShow, Context context) {
        this.context = context;
        this.listShow = listShow;
    }

    public void setListShow(Cursor listShow) {
        this.listShow = listShow;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteShowAdapter.ViewHolder holder, final int pos) {

        String imgUrl = "https://image.tmdb.org/t/p/w185/";

        final TvShow show = getItem(pos);

        Picasso.with(context)
                .load(imgUrl + show.getImgShow())
                .into(holder.imgShow);

        holder.tvTitleShow.setText(show.getTitleShow());
        holder.tvReleaseShow.setText(show.getReleaseShow());
        holder.tvOverviewShow.setText(show.getOverviewShow());

        holder.itemShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, TvShowDetailActivity.class);

                intent.putExtra(TvShowDetailActivity.Extra_Id, show.getId());
                intent.putExtra(TvShowDetailActivity.Extra_Image, show.getImgShow());
                intent.putExtra(TvShowDetailActivity.Extra_Title, show.getTitleShow());
                intent.putExtra(TvShowDetailActivity.Extra_Release, show.getReleaseShow());
                intent.putExtra(TvShowDetailActivity.Extra_Overview, show.getOverviewShow());
                intent.putExtra(TvShowDetailActivity.Extra_Rate, show.getRateShow());
                intent.putExtra(TvShowDetailActivity.Extra_Backdrop, show.getBackShow());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listShow == null) return 0;
        return listShow.getCount();
    }

    private TvShow getItem(int position) {
        if (!listShow.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new TvShow(listShow);
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
