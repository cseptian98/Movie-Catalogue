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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.supports.MovieHelper;
import com.squareup.picasso.Picasso;

import static android.provider.BaseColumns._ID;
import static com.example.moviecatalogue.supports.DataContract.CONTENT_URI;
import static com.example.moviecatalogue.supports.DataContract.MovieColumns.Overview;
import static com.example.moviecatalogue.supports.DataContract.MovieColumns.Poster;
import static com.example.moviecatalogue.supports.DataContract.MovieColumns.Rate;
import static com.example.moviecatalogue.supports.DataContract.MovieColumns.Release_date;
import static com.example.moviecatalogue.supports.DataContract.MovieColumns.Title;

public class MovieDetailActivity extends AppCompatActivity {

    public static String Extra_Id = "Extra_Id";
    public static String Extra_Image = "Extra_Image";
    public static String Extra_Title = "Extra_Title";
    public static String Extra_Release = "Extra_Release";
    public static String Extra_Overview = "Extra_Overview";
    public static String Extra_Rate = "Extra_Rate";

    public Context context;
    public MovieHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView imgMovie = findViewById(R.id.imgMovie);
        TextView tvTitle = findViewById(R.id.tvTitleDetail);
        TextView tvRelease = findViewById(R.id.tvReleaseDetail);
        TextView tvRate = findViewById(R.id.tvRateDetail);
        TextView tvOverview = findViewById(R.id.tvOverview);

        final int movieId = getIntent().getIntExtra(Extra_Id, 0);
        final String picMovie = getIntent().getStringExtra(Extra_Image);
        final String titleMovie = getIntent().getStringExtra(Extra_Title);
        final String releaseMovie = getIntent().getStringExtra(Extra_Release);
        final String rateMovie = getIntent().getStringExtra(Extra_Rate);
        final String overviewMovie = getIntent().getStringExtra(Extra_Overview);

        Picasso.with(context).load("https://image.tmdb.org/t/p/w300/" + picMovie).into(imgMovie);

        tvTitle.setText(titleMovie);
        tvRelease.setText(releaseMovie);
        tvRate.setText(rateMovie);
        tvOverview.setText(overviewMovie);

        final FloatingActionButton fab_fav = findViewById(R.id.fabFavor);
        helper = new MovieHelper(this);
        helper.open();

        if (helper.checkData(movieId)) {
            fab_fav.setImageResource(R.drawable.ic_star);
        }

        fab_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                values.put(_ID, movieId);
                values.put(Poster, picMovie);
                values.put(Title, titleMovie);
                values.put(Release_date, releaseMovie);
                values.put(Rate, rateMovie);
                values.put(Overview, overviewMovie);

                if (!helper.checkData(movieId)) {
                    getContentResolver().insert(CONTENT_URI, values);
                    fab_fav.setImageResource(R.drawable.ic_star);
                    Snackbar.make(v, getResources().getString(R.string.add_success), Snackbar.LENGTH_SHORT).show();
                } else {
                    showAlertDialog(movieId, fab_fav, v);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    private void showAlertDialog(final int movieID, final FloatingActionButton fab_fav, final View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(getResources().getString(R.string.delete));
        alertDialogBuilder
                .setMessage(getResources().getString(R.string.acc_delete_movie))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getContentResolver().delete(Uri.parse(CONTENT_URI + "/" + movieID), null, null);
                        fab_fav.setImageResource(R.drawable.ic_star_border);
                        Snackbar.make(v, getResources().getString(R.string.delete_success), Snackbar.LENGTH_SHORT).show();
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
