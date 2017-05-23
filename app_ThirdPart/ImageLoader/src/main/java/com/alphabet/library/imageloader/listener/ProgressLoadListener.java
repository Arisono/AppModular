package com.alphabet.library.imageloader.listener;

/**
 * Created by Arison on 2017/5/22.
 * 图片加载进度
 */
public interface ProgressLoadListener {

    void update(int bytesRead, int contentLength);

    void onException();

    void onResourceReady();
}
