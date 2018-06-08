package com.android.base.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Arison on 2018/1/24.
 */

public class UnitItems implements Parcelable {
    private String  UnitCode;
    private String  UnitName;
    private int    Price;

    public String getUnitCode() {
        return UnitCode;
    }

    public void setUnitCode(String unitCode) {
        UnitCode = unitCode;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.UnitCode);
        dest.writeString(this.UnitName);
        dest.writeInt(this.Price);
    }

    public UnitItems() {
    }

    protected UnitItems(Parcel in) {
        this.UnitCode = in.readString();
        this.UnitName = in.readString();
        this.Price = in.readInt();
    }
    // 
    public static final Creator<UnitItems> CREATOR = new Creator<UnitItems>() {
        @Override
        public UnitItems createFromParcel(Parcel source) {
            return new UnitItems(source);
        }

        @Override
        public UnitItems[] newArray(int size) {
            return new UnitItems[size];
        }
    };
}