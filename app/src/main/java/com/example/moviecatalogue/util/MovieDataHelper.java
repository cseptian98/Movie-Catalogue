package com.example.moviecatalogue.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.moviecatalogue.util.MovieContract.MovieColumns.*;
import static com.example.moviecatalogue.util.MovieContract.Tab_Favorite;

public class MovieDataHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "db_movie";
    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_FAVORITE = "create table "+ Tab_Favorite + " (" +
            _ID +" integer primary key autoincrement, " +
            Poster +" text not null, " +
            Backdrop +" text not null, " +
            Title +" text not null, " +
            Release_date +" text not null, " +
            Rate +" text not null, " +
            Overview +" text not null );";

    public MovieDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Tab_Favorite);
        onCreate(db);
    }
}
