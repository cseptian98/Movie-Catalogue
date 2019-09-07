package com.example.moviecatalogue.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.moviecatalogue.tvshow.TvShow;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.moviecatalogue.util.ShowContract.Show_Favorite;
import static com.example.moviecatalogue.util.ShowContract.ShowColumns.*;

public class ShowHelper {

    private Context context;
    private ShowDataHelper dbHelper;
    private SQLiteDatabase database;

    public ShowHelper(Context context){
        this.context = context;
    }

    public ShowHelper open() throws SQLException {
        dbHelper = new ShowDataHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public ArrayList<TvShow> getData(){
        Cursor cursor;
        cursor = database.query(Show_Favorite,null,null,null,null,null,_ID+ " DESC",null);
        cursor.moveToFirst();

        ArrayList<TvShow> arrayList = new ArrayList<>();
        TvShow TvShow;
        if (cursor.getCount()>0) {
            do {
                TvShow = new TvShow();
                TvShow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                TvShow.setImgShow(cursor.getString(cursor.getColumnIndexOrThrow(Poster)));
                TvShow.setBackShow(cursor.getString(cursor.getColumnIndexOrThrow(Backdrop)));
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

    public boolean checkData(int id){
        Cursor cursor;
        cursor = database.rawQuery("select * from "+Show_Favorite+" where "+_ID+" = "+id+"",null);
        cursor.moveToFirst();
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public Cursor queryByIdProvider(String id){
        return database.query(Show_Favorite,null,_ID + " = ?",new String[]{id},null,null,null,null);
    }
    public Cursor queryProvider(){
        return database.query(Show_Favorite,null,null,null,null,null,_ID + " DESC");
    }
    public long insertProvider(ContentValues values){
        return database.insert(Show_Favorite,null,values);
    }
    public int updateProvider(String id,ContentValues values){
        return database.update(Show_Favorite,values,_ID + " = '"+id+"'", null);
    }
    public int deleteProvider(String id){
        return database.delete(Show_Favorite, _ID + " = '"+id+"'", null);
    }
}
