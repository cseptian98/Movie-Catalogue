package com.example.moviecatalogue.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.moviecatalogue.movie.Movie;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.moviecatalogue.utility.DataContract.MovieColumns.*;
import static com.example.moviecatalogue.utility.DataContract.Tab_Favorite;

public class MovieHelper {

    private Context context;
    private DataHelper dbHelper;
    private SQLiteDatabase database;

    public MovieHelper(Context context) {
        this.context = context;
    }

    public MovieHelper open() throws SQLException {
        dbHelper = new DataHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<Movie> getData() {
        Cursor cursor;
        cursor = database.query(Tab_Favorite, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();

        ArrayList<Movie> arrayList = new ArrayList<>();
        Movie Movie;
        if (cursor.getCount() > 0) {
            do {
                Movie = new Movie();
                Movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                Movie.setPic(cursor.getString(cursor.getColumnIndexOrThrow(Poster)));
                Movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Title)));
                Movie.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(Release_date)));
                Movie.setRate(cursor.getString(cursor.getColumnIndexOrThrow(Rate)));
                Movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(Overview)));

                arrayList.add(Movie);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public boolean checkData(int id) {
        Cursor cursor;
        cursor = database.rawQuery("select * from " + Tab_Favorite + " where " + _ID + " = " + id + "", null);
        cursor.moveToFirst();
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(Tab_Favorite, null, _ID + " = ?", new String[]{id}, null, null, null, null);
    }

    public Cursor queryProvider() {
        return database.query(Tab_Favorite, null, null, null, null, null, _ID + " DESC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(Tab_Favorite, null, values);
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(Tab_Favorite, values, _ID + " = '" + id + "'", null);
    }

    public int deleteProvider(String id) {
        return database.delete(Tab_Favorite, _ID + " = '" + id + "'", null);
    }

}
