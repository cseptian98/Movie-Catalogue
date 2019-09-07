package com.example.moviecatalogue.util;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class ShowContract {
    public static String Show_Favorite = "show";

    public static final class ShowColumns implements BaseColumns {

        public static String Poster = "poster";
        public static String Backdrop = "backdrop";
        public static String Title = "title";
        public static String Release_date = "release_date";
        public static String Rate = "vote_average";
        public static String Overview = "overview";
    }

    public static final String Author = "com.example.moviecatalogue";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(Author)
            .appendPath(Show_Favorite)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }
}