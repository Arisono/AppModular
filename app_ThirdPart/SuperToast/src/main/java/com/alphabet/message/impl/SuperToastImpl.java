package com.alphabet.message.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;

import com.alphabet.core.utils.Utils;
import com.alphabet.message.BaseToast;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.orhanobut.logger.Logger;
import com.tapadoo.alerter.Alerter;

/**
 * 暂时做成工具类，后面调整灵活切换
 * Created by Arison on 2017/5/25.
 */
public class SuperToastImpl implements BaseToast{
    
    private static SuperToastImpl instance;
    
    public static SuperToastImpl getInstance(){
        if (instance==null){
            synchronized (SuperToastImpl.class){
                if (instance==null){
                    instance=new SuperToastImpl();
                }
            }
        }
        return  instance;
    }
    
    

    @Override
    public void show(String text) {
        new SuperToast(Utils.getContext()).setText(text)
                .setDuration(Style.DURATION_VERY_LONG)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.DARK_GREY))
                .setAnimations(Style.ANIMATIONS_FADE)
                .setFrame(Style.FRAME_STANDARD)
                .show();
    }

    @Override
    public void showAtTop(String text) {
        new SuperToast(Utils.getContext()).setText(text)
                .setDuration(Style.DURATION_VERY_LONG)
                .setColor(Color.parseColor("#A6424242"))
                .setAnimations(Style.ANIMATIONS_FADE)
                .setFrame(Style.FRAME_STANDARD)
                .setGravity(Gravity.TOP)
                .show();
    }

    @Override
    public void showAtCenter(String text) {
        new SuperToast(Utils.getContext()).setText(text)
                .setDuration(Style.DURATION_VERY_LONG)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.DARK_GREY))
                .setAnimations(Style.ANIMATIONS_FADE)
                .setFrame(Style.FRAME_STANDARD)
                .setGravity(Gravity.CENTER)
                .show();
    }

    @Override
    public void showAtBottom(String text) {
        new SuperToast(Utils.getContext()).setText(text)
                .setDuration(Style.DURATION_VERY_LONG)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.DARK_GREY))
                .setAnimations(Style.ANIMATIONS_FADE)
                .setFrame(Style.FRAME_STANDARD)
                .setGravity(Gravity.BOTTOM)
                .show();
    }

    @Override
    public void showAtNotiftion(String text) {
        new SuperToast(Utils.getContext()).setText(text)
                .setDuration(Style.DURATION_VERY_LONG)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.DARK_GREY))
                .setAnimations(Style.ANIMATIONS_FADE)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setGravity(Gravity.TOP)
                .show();

      
    }

    @Override
    public void show(Context ct, String text) {
        SuperActivityToast.cancelAllSuperToasts();
        SuperActivityToast.create(ct, Style.red(),
                Style.TYPE_STANDARD)
                .setIndeterminate(false)
                .setText(text)
                .setAnimations(Style.ANIMATIONS_FADE)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.DARK_GREY))
                .setFrame(Style.FRAME_STANDARD)
                //.setGravity(Gravity.BOTTOM | Gravity.CENTER)
                .show();
    }

    @Override
    public void showAtTop(Context ct, String text) {

    }

    @Override
    public void showAtCenter(Context ct, String text) {
        SuperActivityToast.cancelAllSuperToasts();
        SuperActivityToast.create(ct, Style.red(),
                Style.TYPE_BUTTON)
                .setButtonDividerColor(PaletteUtils.getTransparentColor(PaletteUtils.WHITE))
                .setIndeterminate(false)
                .setButtonText("取消")
                .setOnButtonClickListener("cancle", null, new SuperActivityToast.OnButtonClickListener() {
                    @Override
                    public void onClick(View view, Parcelable token) {
                        Logger.d("点击取消...");
                    }
                })
                        // .setTouchToDismiss(false)
                .setText(text)
                .setAnimations(Style.ANIMATIONS_FADE)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.DARK_GREY))
                .setFrame(Style.FRAME_LOLLIPOP)
                .setGravity(Gravity.CENTER)
                .show();
    }

    @Override
    public void showAtBottom(Context ct, String text) {
        SuperActivityToast.cancelAllSuperToasts();
        SuperActivityToast.create(ct, Style.red(),
                Style.TYPE_BUTTON)
                .setIndeterminate(false)
                .setButtonText("取消")
                .setOnButtonClickListener("cancle", null, new SuperActivityToast.OnButtonClickListener() {
                    @Override
                    public void onClick(View view, Parcelable token) {
                        Logger.d("点击取消...");
                    }
                })
               // .setTouchToDismiss(false)
                .setText(text)
                        // .setIconResource(Style.ICONPOSITION_LEF)
                .setAnimations(Style.ANIMATIONS_FADE)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.DARK_GREY))
                .setFrame(Style.FRAME_LOLLIPOP)
               // .setGravity(Gravity.BOTTOM | Gravity.DISPLAY_CLIP_HORIZONTAL)
                .show();
        
    }

    @Override
    public void showAtNotiftion(Context ct, String text) {
        Alerter.create((Activity) ct)
                .setTitle(((Activity) ct).getTitle().toString())
                .setText(text)
                .show();
    }
}
