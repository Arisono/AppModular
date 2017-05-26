package com.alphabet.message.toast;

import android.content.Context;
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

/**
 * 暂时做成工具类，后面调整灵活切换
 * Created by Arison on 2017/5/25.
 */
public class ToastUtils implements BaseToast{
    
    private static ToastUtils instance;
    
    public static ToastUtils getInstance(){
        if (instance==null){
            synchronized (ToastUtils.class){
                if (instance==null){
                    instance=new ToastUtils();
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

    }

    @Override
    public void showAtCenter(String text) {

    }

    @Override
    public void showAtBottom(String text) {

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
        SuperActivityToast.create(ct, Style.red(),
                Style.TYPE_BUTTON)
                .setIndeterminate(true)
                .setButtonText("取消")
                .setOnButtonClickListener("cancle", null, new SuperActivityToast.OnButtonClickListener() {
                    @Override
                    public void onClick(View view, Parcelable token) {
                        Logger.d("点击取消...");
                    }
                })
                .setTouchToDismiss(false)
                .setText(text)
                        // .setIconResource(Style.ICONPOSITION_LEF)
                .setAnimations(Style.ANIMATIONS_FADE)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.DARK_GREY))
                .setFrame(Style.FRAME_STANDARD)
                .setGravity(Gravity.BOTTOM | Gravity.CENTER)
                .show();
    }

    @Override
    public void showAtNotiftion(Context ct, String text) {

    }
}
