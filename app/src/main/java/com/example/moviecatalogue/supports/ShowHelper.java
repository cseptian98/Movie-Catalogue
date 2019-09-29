package com.example.moviecatalogue.supports;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.moviecatalogue.tvshow.TvShow;

import java.util.ArrayList;

import static com.example.moviecatalogue.supports.DataContract.ShowColumns.Overview;
import static com.example.moviecatalogue.supports.DataContract.ShowColumns.Poster;
import static com.example.moviecatalogue.supports.DataContract.ShowColumns.Rate;
import static com.example.moviecatalogue.supports.DataContract.ShowColumns.Release_date;
import static com.example.moviecatalogue.supports.DataContract.ShowColumns.Title;
import static com.example.moviecatalogue.supports.DataContract.ShowColumns._ID_Show;
import static com.example.moviecatalogue.supports.DataContract.Tab_Favorite_Show;

public class ShowHelper {

    private Context context;
    private DataHelper dbHelper;
    private SQLiteDatabase database;

    public ShowHelper(Context context) {
        this.context = context;
    }

    public ShowHelper open() throws SQLException {
        dbHelper = new DataHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<TvShow> getData() {
        Cursor cursor;
        cursor = database.query(Tab_Favorite_Show, null, null, null, null, null, _ID_Show + " DESC", null);
        cursor.moveToFirst();

        ArrayList<TvShow> arrayList = new ArrayList<>();
        TvShow TvShow;
        if (cursor.getCount() > 0) {
            do {
                TvShow = new TvShow();
                TvShow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID_Show)));
                TvShow.setImgShow(cursor.getString(cursor.getColumnIndexOrThrow(Poster)));
                TvShow.setTitleShow(cursor.getString(cursor.getColumnIndexOrThrow(Title)));
                TvShow.setReleaseShow(cursor.getString(cursor.getColumnIndexOrThrow(Release_date)));
                TvShow.setRateShow(cursor.getString(cursor.getColumnIndexOrThrow(Rate)));
                TvShow.setOverviewShow(cursor.getString(cursor.getColumnIndexOrThrow(Overview)));

                arrayList.add(TvShow);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public boolean checkData(int id_show) {
        Cursor cursor;
        cursor = database.rawQuery("select * from " + Tab_Favorite_Show + " where " + _ID_Show + " = " + id_show + "", null);
        cursor.moveToFirst();
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public Cursor queryByIdProvider(String id_show) {
        return database.query(Tab_Favorite_Show, null, _ID_Show + " = ?", new String[]{id_show}, null, null, null, null);
    }

    public Cursor queryProvider() {
        return database.query(Tab_Favorite_Show, null, null, null, null, null, _ID_Show + " DESC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(Tab_Favorite_Show, null, values);
    }

    public int updateProvider(String id_show, ContentValues values) {
        return database.update(Tab_Favorite_Show, values, _ID_Show + " = '" + id_show + "'", null);
    }

    public int deleteProvider(String id_show) {
        return database.delete(Tab_Favorite_Show, _ID_Show + " = '" + id_show + "'", null);
    }
}
