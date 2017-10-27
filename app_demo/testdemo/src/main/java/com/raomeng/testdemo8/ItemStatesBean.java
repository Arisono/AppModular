package com.raomeng.testdemo8;

/**
 * Created by RaoMeng on 2017/8/15.
 */
public class ItemStatesBean implements Cloneable {
    private String mOption;
    private boolean mState = false;

    public String getOption() {
        return mOption;
    }

    public void setOption(String option) {
        mOption = option;
    }

    public boolean isState() {
        return mState;
    }

    public void setState(boolean state) {
        mState = state;
    }

    @Override
    protected ItemStatesBean clone() throws CloneNotSupportedException {
        return (ItemStatesBean) super.clone();
    }

    @Override
    public String toString() {
        return "Cposition1ItemStatesBean{" +
                "mOption='" + mOption + '\'' +
                ", mState=" + mState +
                '}';
    }
}
