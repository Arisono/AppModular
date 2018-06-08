package com.android.base.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Arison on 2018/1/24.
 */

public class Product implements Parcelable {

    private String   Code;
    private int DishCategoryId;//类别 索引
    private String BusinessId="1000123";//商家编号
    private String   DishCategoryCode;
    private String   DishCategoryName;
    private String   Name;
    private String   HelpCode;
    private boolean   IsWeightConfim;
    
    private List<UnitItems> UnitItems;
    
    public Product() {
    }

    public String getBusinessId() {
        return BusinessId;
    }

    public void setBusinessId(String businessId) {
        BusinessId = businessId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public int getDishCategoryId() {
        return DishCategoryId;
    }

    public void setDishCategoryId(int dishCategoryId) {
        DishCategoryId = dishCategoryId;
    }

    public String getDishCategoryCode() {
        return DishCategoryCode;
    }

    public void setDishCategoryCode(String dishCategoryCode) {
        DishCategoryCode = dishCategoryCode;
    }

    public String getDishCategoryName() {
        return DishCategoryName;
    }

    public void setDishCategoryName(String dishCategoryName) {
        DishCategoryName = dishCategoryName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHelpCode() {
        return HelpCode;
    }

    public void setHelpCode(String helpCode) {
        HelpCode = helpCode;
    }

    public boolean isWeightConfim() {
        return IsWeightConfim;
    }

    public void setWeightConfim(boolean weightConfim) {
        IsWeightConfim = weightConfim;
    }

    public List<UnitItems> getUnitItems() {
        return UnitItems;
    }

    public void setUnitItems(List<UnitItems> unitItems) {
        UnitItems = unitItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Code);
        dest.writeInt(this.DishCategoryId);
        dest.writeString(this.BusinessId);
        dest.writeString(this.DishCategoryCode);
        dest.writeString(this.DishCategoryName);
        dest.writeString(this.Name);
        dest.writeString(this.HelpCode);
        dest.writeByte(this.IsWeightConfim ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.UnitItems);
    }

    protected Product(Parcel in) {
        this.Code = in.readString();
        this.DishCategoryId = in.readInt();
        this.BusinessId = in.readString();
        this.DishCategoryCode = in.readString();
        this.DishCategoryName = in.readString();
        this.Name = in.readString();
        this.HelpCode = in.readString();
        this.IsWeightConfim = in.readByte() != 0;
        this.UnitItems = in.createTypedArrayList(com.android.base.model.UnitItems.CREATOR);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
