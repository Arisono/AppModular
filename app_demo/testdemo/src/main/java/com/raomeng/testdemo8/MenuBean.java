package com.raomeng.testdemo8;

import java.util.List;

/**
 * Created by RaoMeng on 2017/8/11.
 */
public class MenuBean implements Cloneable {
    private String mCaption;
    private String mType;
    private int position;
    private List<ItemStatesBean> mDefaultValues;


    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public List<ItemStatesBean> getDefaultValues() {
        return mDefaultValues;
    }

    public void setDefaultValues(List<ItemStatesBean> defaultValues) {
        mDefaultValues = defaultValues;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    protected MenuBean clone() throws CloneNotSupportedException {
        return (MenuBean) super.clone();
    }

    @Override
    public String toString() {
        return "MenuBean{" +
                "mCaption='" + mCaption + '\'' +
                ", mType='" + mType + '\'' +
                ", mDefaultValues=" + mDefaultValues +
                '}';
    }
}
