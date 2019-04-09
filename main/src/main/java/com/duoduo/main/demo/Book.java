package com.duoduo.main.demo;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    private String name;

    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeLong(this.id);
    }

    public void readFromParcel(Parcel dest) {
        this.name = dest.readString();
        this.id = dest.readLong();
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.name = in.readString();
        this.id = in.readLong();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
