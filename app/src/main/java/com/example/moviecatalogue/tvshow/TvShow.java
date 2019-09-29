package com.example.moviecatalogue.tvshow;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.moviecatalogue.supports.DataContract;

import org.json.JSONObject;

import static com.example.moviecatalogue.supports.DataContract.getColumnInt;
import static com.example.moviecatalogue.supports.DataContract.getColumnString;

public class TvShow implements Parcelable {
    private int id;
    private String imgShow;
    private String titleShow;
    private String releaseShow;
    private String rateShow;
    private String overviewShow;
    private String backShow;

    public TvShow(Cursor cursor) {
        this.id = getColumnInt(cursor, DataContract.ShowColumns._ID_Show);
        this.imgShow = getColumnString(cursor, DataContract.ShowColumns.Poster);
        this.titleShow = getColumnString(cursor, DataContract.ShowColumns.Title);
        this.overviewShow = getColumnString(cursor, DataContract.ShowColumns.Overview);
        this.releaseShow = getColumnString(cursor, DataContract.ShowColumns.Release_date);
        this.rateShow = getColumnString(cursor, DataContract.ShowColumns.Rate);
    }

    public TvShow(JSONObject object) {
        try {
            int id = object.getInt("id");
            String poster = object.getString("poster_path");
            String title = object.getString("name");
            String overview = object.getString("overview");
            String release = object.getString("first_air_date");
            String rate = object.getString("vote_average");
            String backdrop = object.getString("backdrop_path");

            this.id = id;
            this.imgShow = poster;
            this.titleShow = title;
            this.overviewShow = overview;
            this.releaseShow = release;
            this.rateShow = rate;
            this.backShow = backdrop;

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

    public String getImgShow() {
        return imgShow;
    }

    public void setImgShow(String imgShow) {
        this.imgShow = imgShow;
    }

    public String getTitleShow() {
        return titleShow;
    }

    public void setTitleShow(String titleShow) {
        this.titleShow = titleShow;
    }

    public String getReleaseShow() {
        return releaseShow;
    }

    public void setReleaseShow(String releaseShow) {
        this.releaseShow = releaseShow;
    }

    public String getRateShow() {
        return rateShow;
    }

    public void setRateShow(String rateShow) {
        this.rateShow = rateShow;
    }

    public String getOverviewShow() {
        return overviewShow;
    }

    public void setOverviewShow(String overviewShow) {
        this.overviewShow = overviewShow;
    }

    public String getBackShow() {
        return backShow;
    }

    public void setBackShow(String backShow) {
        this.backShow = backShow;
    }

    public TvShow() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.imgShow);
        dest.writeString(this.titleShow);
        dest.writeString(this.releaseShow);
        dest.writeString(this.rateShow);
        dest.writeString(this.overviewShow);
        dest.writeString(this.backShow);
    }

    protected TvShow(Parcel in) {
        this.id = in.readInt();
        this.imgShow = in.readString();
        this.titleShow = in.readString();
        this.releaseShow = in.readString();
        this.rateShow = in.readString();
        this.overviewShow = in.readString();
        this.backShow = in.readString();
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
