package com.example.favoritemovie.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.favoritemovie.util.DataContract;

import static com.example.favoritemovie.util.DataContract.getColumnInt;
import static com.example.favoritemovie.util.DataContract.getColumnString;

public class Movie implements Parcelable {
    private int id;
    private String pic;
    private String rate;
    private String title;
    private String release_date;
    private String overview;
    private String backdrop;

    public Movie(Cursor cursor) {
        this.id = getColumnInt(cursor, DataContract.MovieColumns._ID);
        this.pic = getColumnString(cursor, DataContract.MovieColumns.Poster);
        this.title = getColumnString(cursor, DataContract.MovieColumns.Title);
        this.overview = getColumnString(cursor, DataContract.MovieColumns.Overview);
        this.release_date = getColumnString(cursor, DataContract.MovieColumns.Release_date);
        this.rate = getColumnString(cursor, DataContract.MovieColumns.Rate);
        this.backdrop = getColumnString(cursor, DataContract.MovieColumns.Backdrop);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.pic);
        dest.writeString(this.rate);
        dest.writeString(this.title);
        dest.writeString(this.release_date);
        dest.writeString(this.overview);
        dest.writeString(this.backdrop);
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.pic = in.readString();
        this.rate = in.readString();
        this.title = in.readString();
        this.release_date = in.readString();
        this.overview = in.readString();
        this.backdrop = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
