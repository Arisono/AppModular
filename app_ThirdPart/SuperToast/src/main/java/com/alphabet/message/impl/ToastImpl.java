package com.alphabet.message.impl;

import android.content.Context;
import android.view.Gravity;

import com.alphabet.core.utils.ScreenUtils;
import com.alphabet.core.utils.ToastUtils;
import com.alphabet.message.BaseToast;

/**
 * 原生Toast消息实现类
 * 拓展：支持自定义动画，支持长时间停留，支持点击事件
 * Created by Arison on 2017/6/6.
 */
public class ToastImpl implements BaseToast {

    private static ToastImpl instance;

    public static ToastImpl getInstance(){
        if (instance==null){
            synchronized (ToastImpl.class){
                if (instance==null){
                    instance=new ToastImpl();
                }
            }
        }
        return  instance;
    }
    
    @Override
    public void show(String text) {
        ToastUtils.showLong(text);
    }

    @Override
    public void showAtTop(String text) {
        ToastUtils.setGravity(Gravity.TOP,0, ScreenUtils.dip2px(50));
        ToastUtils.showLong(text);
    }

    @Override
    public void showAtCenter(String text) {
        ToastUtils.setGravity(Gravity.CENTER,0, 0);
        ToastUtils.showLong(text);
    }

    @Override
    public void showAtBottom(String text) {
        ToastUtils.setGravity(Gravity.BOTTOM,0, ScreenUtils.dip2px(80));
        ToastUtils.showLong(text);
    }

    @Override
    public void showAtNotiftion(String text) {

    }

    @Override
    public void show(Context ct, String text) {

    }

    @Override
    public void showAtTop(Context ct, String text) {

    }

    @Override
    public void showAtCenter(Context ct, String text) {

    }

    @Override
    public void showAtBottom(Context ct, String text) {

    }

    @Override
    public void showAtNotiftion(Context ct, String text) {

    }
}
