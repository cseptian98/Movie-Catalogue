package com.example.moviecatalogue.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.example.moviecatalogue.util.ShowContract;
import com.example.moviecatalogue.util.ShowHelper;

import static com.example.moviecatalogue.util.ShowContract.Author;
import static com.example.moviecatalogue.util.ShowContract.CONTENT_URI;

public class TvShowProvider extends ContentProvider {

    private static final int SHOW = 1;
    private static final int SHOW_ID = 2;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(Author, ShowContract.Show_Favorite, SHOW);
        sUriMatcher.addURI(Author, ShowContract.Show_Favorite+ "/#", SHOW_ID);
    }

    private ShowHelper showHelper;

    @Override
    public boolean onCreate() {
        showHelper = new ShowHelper(getContext());
        showHelper.open();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch(sUriMatcher.match(uri)){
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

        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(),uri);
        }

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long added ;

        switch (sUriMatcher.match(uri)){
            case SHOW:
                added = showHelper.insertProvider(values);
                break;
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
            case SHOW_ID:
                deleted =  showHelper.deleteProvider(uri.getLastPathSegment());
                break;
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
        int updated ;
        switch (sUriMatcher.match(uri)) {
            case SHOW_ID:
                updated = showHelper.updateProvider(uri.getLastPathSegment(),values);
                break;
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
