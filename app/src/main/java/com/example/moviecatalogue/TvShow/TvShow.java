package com.example.moviecatalogue.TvShow;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private int poster;
    private String judul;
    private String rilis;
    private String durasi;
    private String overview;

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public TvShow() {
    }

    public String getRilis() {
        return rilis;
    }

    public void setRilis(String rilis) {
        this.rilis = rilis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.poster);
        dest.writeString(this.judul);
        dest.writeString(this.rilis);
        dest.writeString(this.durasi);
        dest.writeString(this.overview);
    }

    protected TvShow(Parcel in) {
        this.poster = in.readInt();
        this.judul = in.readString();
        this.rilis = in.readString();
        this.durasi = in.readString();
        this.overview = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
