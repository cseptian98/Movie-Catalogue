package com.example.moviecatalogue.supports;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.moviecatalogue.supports.DataContract.ShowColumns.Overview;
import static com.example.moviecatalogue.supports.DataContract.ShowColumns.Poster;
import static com.example.moviecatalogue.supports.DataContract.ShowColumns.Rate;
import static com.example.moviecatalogue.supports.DataContract.ShowColumns.Release_date;
import static com.example.moviecatalogue.supports.DataContract.ShowColumns.Title;
import static com.example.moviecatalogue.supports.DataContract.ShowColumns._ID_Show;
import static com.example.moviecatalogue.supports.DataContract.Tab_Favorite;
import static com.example.moviecatalogue.supports.DataContract.Tab_Favorite_Show;

public class DataHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "db_catalogue";
    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_FAVORITE = "create table " + Tab_Favorite + "(" +
            _ID + " integer primary key autoincrement, " +
            Poster + " text not null, " +
            Title + " text not null, " +
            Release_date + " text not null, " +
            Rate + " text not null, " +
            Overview + " text not null );";

    public static String CREATE_SHOW_FAVORITE = "create table " + Tab_Favorite_Show + "(" +
            _ID_Show + " integer primary key autoincrement, " +
            Poster + " text not null, " +
            Title + " text not null, " +
            Release_date + " text not null, " +
            Rate + " text not null, " +
            Overview + " text not null );";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SHOW_FAVORITE);
        db.execSQL(CREATE_TABLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Tab_Favorite_Show);
        db.execSQL("DROP TABLE IF EXISTS " + Tab_Favorite);
        onCreate(db);
    }
}
