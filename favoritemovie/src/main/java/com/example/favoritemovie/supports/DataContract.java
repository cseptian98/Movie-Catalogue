package com.example.favoritemovie.supports;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DataContract {

    public static String Tab_Favorite = "favorite";

    public static final class MovieColumns implements BaseColumns {

        public static String Poster = "poster";
        public static String Title = "title";
        public static String Release_date = "release_date";
        public static String Rate = "vote_average";
        public static String Overview = "overview";
    }

    public static final String Author = "com.example.moviecatalogue";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(Author)
            .appendPath(Tab_Favorite)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) { return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
}
