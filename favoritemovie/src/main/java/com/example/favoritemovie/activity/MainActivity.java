package com.example.favoritemovie.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.favoritemovie.R;
import com.example.favoritemovie.adapter.FavoriteMovieAdapter;
import com.example.favoritemovie.util.DataContract;

import static com.example.favoritemovie.util.DataContract.CONTENT_URI;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {

    private FavoriteMovieAdapter adapter;
    ListView rv;

    private final int Load_Movie_Id = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.listFav);
        adapter = new FavoriteMovieAdapter(this, null,true);
        rv.setAdapter(adapter);
        rv.setOnItemClickListener(this);

        getSupportLoaderManager().initLoader(Load_Movie_Id, null, this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        getSupportLoaderManager().restartLoader(Load_Movie_Id, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args){
        return new CursorLoader(this, CONTENT_URI, null, null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data){
        adapter.swapCursor(data);
    }
    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(Load_Movie_Id);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long i) {
        Cursor cursor = (Cursor) adapter.getItem(position);

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DataContract.MovieColumns._ID));
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.setData(Uri.parse(CONTENT_URI + "/" + id));
        startActivity(intent);
    }
}
