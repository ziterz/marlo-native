package com.ziterz.marlo.User.Item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ziter on 4/22/2017.
 */

public class LaundryData implements Parcelable {

    String title;
    String alamat;
    Double rating;
    String thumbnail;
    String jarak;

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public LaundryData(String title, String alamat, Double rating, String thumbnail, String jarak) {
        this.title = title;
        this.alamat = alamat;
        this.rating = rating;
        this.thumbnail = thumbnail;
        this.jarak = jarak;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public LaundryData(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.alamat);
        dest.writeDouble(this.rating);
        dest.writeString(this.thumbnail);
        dest.writeString(this.jarak);
    }
    protected LaundryData(Parcel in) {
        this.title = in.readString();
        this.alamat = in.readString();
        this.rating = in.readDouble();
        this.thumbnail = in.readString();
        this.jarak = in.readString();
    }
    public static final Parcelable.Creator<LaundryData> CREATOR = new Parcelable.Creator<LaundryData>() {
        @Override
        public LaundryData createFromParcel(Parcel source) {
            return new LaundryData(source);
        }

        @Override
        public LaundryData[] newArray(int size) {
            return new LaundryData[size];
        }
    };
}
