package com.android.base.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Arison on 2018/1/30.
 */
//购物车
public class ShoppingEntity implements Parcelable {
 
    String id;
    String name;
    int quantity;
    double unitPrice;
    double totalPrice;
    Product product;

    public static ShoppingEntity initWithProduct(Product product) {
        ShoppingEntity entity = new ShoppingEntity();
        try {
            entity.setId(product.getCode());
            entity.setName(product.getName());
            entity.setUnitPrice(product.getUnitItems().get(0).getPrice());
            entity.setQuantity(1);
            entity.setProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = this.quantity * this.unitPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.quantity);
        dest.writeDouble(this.unitPrice);
        dest.writeDouble(this.totalPrice);
        dest.writeParcelable(this.product, flags);
    }

    public ShoppingEntity() {
    }

    protected ShoppingEntity(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.quantity = in.readInt();
        this.unitPrice = in.readDouble();
        this.totalPrice = in.readDouble();
        this.product = in.readParcelable(Product.class.getClassLoader());
    }

    public static final Creator<ShoppingEntity> CREATOR = new Creator<ShoppingEntity>() {
        @Override
        public ShoppingEntity createFromParcel(Parcel source) {
            return new ShoppingEntity(source);
        }

        @Override
        public ShoppingEntity[] newArray(int size) {
            return new ShoppingEntity[size];
        }
    };
}
