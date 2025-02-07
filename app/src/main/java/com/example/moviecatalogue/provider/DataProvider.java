package com.example.moviecatalogue.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.example.moviecatalogue.supports.DataContract;
import com.example.moviecatalogue.supports.MovieHelper;
import com.example.moviecatalogue.supports.ShowHelper;

import static com.example.moviecatalogue.supports.DataContract.Author;
import static com.example.moviecatalogue.supports.DataContract.Author_Show;
import static com.example.moviecatalogue.supports.DataContract.CONTENT_URI;

public class DataProvider extends ContentProvider {

    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;
    private static final int SHOW = 3;
    private static final int SHOW_ID = 4;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(Author, DataContract.Tab_Favorite, MOVIE);
        sUriMatcher.addURI(Author, DataContract.Tab_Favorite + "/#", MOVIE_ID);

        sUriMatcher.addURI(Author_Show, DataContract.Tab_Favorite_Show, SHOW);
        sUriMatcher.addURI(Author_Show, DataContract.Tab_Favorite_Show + "/#", SHOW_ID);
    }

    private MovieHelper movieHelper;
    private ShowHelper showHelper;

    @Override
    public boolean onCreate() {
        movieHelper = new MovieHelper(getContext());
        movieHelper.open();
        showHelper = new ShowHelper(getContext());
        showHelper.open();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                cursor = movieHelper.queryProvider();
                break;
            case MOVIE_ID:
                cursor = movieHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            case SHOW:
                cursor = showHelper.queryProvider();
                break;
            case SHOW_ID:
                cursor = showHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long added;

        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                added = movieHelper.insertProvider(values);
                break;
            case SHOW:
                added = showHelper.insertProvider(values);
            default:
                added = 0;
                break;
        }

        if (added > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_ID:
                deleted = movieHelper.deleteProvider(uri.getLastPathSegment());
                break;
            case SHOW_ID:
                deleted = showHelper.deleteProvider(uri.getLastPathSegment());
            default:
                deleted = 0;
                break;
        }

        if (deleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int updated;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_ID:
                updated = movieHelper.updateProvider(uri.getLastPathSegment(), values);
                break;
            case SHOW_ID:
                updated = showHelper.updateProvider(uri.getLastPathSegment(), values);
            default:
                updated = 0;
                break;
        }

        if (updated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updated;
    }
}
