package com.example.moviecatalogue.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.util.ShowHelper;
import com.squareup.picasso.Picasso;

import static com.example.moviecatalogue.util.DataContract.CONTENT_URI_SHOW;
import static com.example.moviecatalogue.util.DataContract.ShowColumns.Backdrop;
import static com.example.moviecatalogue.util.DataContract.ShowColumns.Overview;
import static com.example.moviecatalogue.util.DataContract.ShowColumns.Poster;
import static com.example.moviecatalogue.util.DataContract.ShowColumns.Rate;
import static com.example.moviecatalogue.util.DataContract.ShowColumns.Release_date;
import static com.example.moviecatalogue.util.DataContract.ShowColumns.Title;
import static com.example.moviecatalogue.util.DataContract.ShowColumns._ID_Show;

public class TvShowDetailActivity extends AppCompatActivity {

    public static String Extra_Id = "Extra_Id";
    public static String Extra_Image = "Extra_Image";
    public static String Extra_Title = "Extra_Title";
    public static String Extra_Release = "Extra_Release";
    public static String Extra_Overview = "Extra_Overview";
    public static String Extra_Rate = "Extra_Rate";
    public static String Extra_Backdrop = "Extra_Backdrop";

    public Context context;
    public ShowHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imgTvShow = findViewById(R.id.imgMovie);
        TextView tvTitle = findViewById(R.id.tvTitleDetail);
        TextView tvRelease = findViewById(R.id.tvReleaseDetail);
        TextView tvRate = findViewById(R.id.tvRateDetail);
        TextView tvOverview = findViewById(R.id.tvOverview);
        ImageView backShow = findViewById(R.id.backMovie);

        final int showId = getIntent().getIntExtra(Extra_Id, 0);
        final String picShow = getIntent().getStringExtra(Extra_Image);
        final String titleShow = getIntent().getStringExtra(Extra_Title);
        final String releaseShow = getIntent().getStringExtra(Extra_Release);
        final String rateShow = getIntent().getStringExtra(Extra_Rate);
        final String overviewShow = getIntent().getStringExtra(Extra_Overview);
        final String backdropShow = getIntent().getStringExtra(Extra_Backdrop);

        Picasso.with(context).load("https://image.tmdb.org/t/p/w300/" + picShow).into(imgTvShow);
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500/" + backdropShow).into(backShow);

        tvTitle.setText(titleShow);
        tvRelease.setText(releaseShow);
        tvRate.setText(rateShow);
        tvOverview.setText(overviewShow);

        final FloatingActionButton fab_fav = findViewById(R.id.fabFavor);
        helper = new ShowHelper(this);
        helper.open();

        if (helper.checkData(showId)) {
            fab_fav.setImageResource(R.drawable.ic_star);
        }

        fab_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                values.put(_ID_Show, showId);
                values.put(Poster, picShow);
                values.put(Backdrop, backdropShow);
                values.put(Title, titleShow);
                values.put(Release_date, releaseShow);
                values.put(Rate, rateShow);
                values.put(Overview, overviewShow);

                if (!helper.checkData(showId)) {
                    getContentResolver().insert(CONTENT_URI_SHOW, values);
                    fab_fav.setImageResource(R.drawable.ic_star);
                    Snackbar.make(v, getResources().getString(R.string.add_success), Snackbar.LENGTH_SHORT).show();
                } else {
                    showAlertDialog(showId, fab_fav, v);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    private void showAlertDialog(final int showID, final FloatingActionButton fab_fav, final View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(getResources().getString(R.string.delete));
        alertDialogBuilder
                .setMessage(getResources().getString(R.string.acc_delete_show))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getContentResolver().delete(Uri.parse(CONTENT_URI_SHOW + "/" + showID), null, null);
                        fab_fav.setImageResource(R.drawable.ic_star_border);
                        Snackbar.make(v, getResources().getString(R.string.delete_success2), Snackbar.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}