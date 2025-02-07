package com.example.moviecatalogue.movie;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.moviecatalogue.supports.DataContract;

import org.json.JSONObject;

import static com.example.moviecatalogue.supports.DataContract.getColumnInt;
import static com.example.moviecatalogue.supports.DataContract.getColumnString;

public class Movie implements Parcelable {
    private int id;
    private String pic;
    private String rate;
    private String title;
    private String release_date;
    private String overview;
    private String backdrop;

    public Movie() {
    }

    public Movie(Cursor cursor) {
        this.id = getColumnInt(cursor, DataContract.MovieColumns._ID);
        this.pic = getColumnString(cursor, DataContract.MovieColumns.Poster);
        this.title = getColumnString(cursor, DataContract.MovieColumns.Title);
        this.overview = getColumnString(cursor, DataContract.MovieColumns.Overview);
        this.release_date = getColumnString(cursor, DataContract.MovieColumns.Release_date);
        this.rate = getColumnString(cursor, DataContract.MovieColumns.Rate);
    }

    public Movie(JSONObject object) {
        try {
            int id = object.getInt("id");
            String poster = object.getString("poster_path");
            String title = object.getString("title");
            String overview = object.getString("overview");
            String release = object.getString("release_date");
            String rate = object.getString("vote_average");
            String backdrop = object.getString("backdrop_path");

            this.id = id;
            this.pic = poster;
            this.title = title;
            this.overview = overview;
            this.release_date = release;
            this.rate = rate;
            this.backdrop = backdrop;

        } catch (Exception e) {

            e.printStackTrace();

        }
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
