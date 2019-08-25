package com.example.moviecatalogue.Movie;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private int pic;
    private String duration;
    private String title;
    private String release_date;
    private String overview;

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public Movie() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pic);
        dest.writeString(this.duration);
        dest.writeString(this.title);
        dest.writeString(this.release_date);
        dest.writeString(this.overview);
    }

    protected Movie(Parcel in) {
        this.pic = in.readInt();
        this.duration = in.readString();
        this.title = in.readString();
        this.release_date = in.readString();
        this.overview = in.readString();
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
