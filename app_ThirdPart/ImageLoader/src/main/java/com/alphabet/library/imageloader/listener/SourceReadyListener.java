package com.alphabet.library.imageloader.listener;

/**
 * 图片加载前
 * 通知准备就绪
 */
public interface SourceReadyListener {

    void onResourceReady(int width, int height);
}
