package com.example.rahul.famousbiography.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rahul on 27/12/2015.
 */
public class FamousPeople implements Parcelable {
    private static final String FAMOUS_NAME = "famous name";
    private static final String FAMOUS_PHOTO="photo";



    private long id;
    private String name;
    private String photo;
    private String details;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getPhoto() {
        return photo;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDetails() {
        return details;
    }


    public void setDetails(String details) {
        this.details = details;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.photo);
        dest.writeString(this.details);
    }

    public FamousPeople() {
    }

    protected FamousPeople(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.photo = in.readString();
        this.details = in.readString();
    }

    public static final Parcelable.Creator<FamousPeople> CREATOR = new Parcelable.Creator<FamousPeople>() {
        @Override
        public FamousPeople createFromParcel(Parcel source) {
            return new FamousPeople(source);
        }

        @Override
        public FamousPeople[] newArray(int size) {
            return new FamousPeople[size];
        }
    };
}


