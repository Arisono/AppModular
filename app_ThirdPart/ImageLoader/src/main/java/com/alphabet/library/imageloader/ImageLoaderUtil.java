
package com.alphabet.library.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.alphabet.library.imageloader.iml.GlideImageLoaderStrategy;
import com.alphabet.library.imageloader.listener.ImageSaveListener;
import com.alphabet.library.imageloader.listener.ProgressLoadListener;
import com.alphabet.library.imageloader.listener.SourceReadyListener;

public class ImageLoaderUtil{


    private static ImageLoaderUtil mInstance;
    private BaseImageLoaderStrategy mStrategy;

    public ImageLoaderUtil() {
        mStrategy = new GlideImageLoaderStrategy();
    }

    //单例模式，节省资源
    public static ImageLoaderUtil getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoaderUtil.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderUtil();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }


    public void loadImage(String url, int placeholder, ImageView imageView) {
        mStrategy.loadImage(imageView.getContext(), url, placeholder, imageView);
    }

    public void loadGifImage(String url, int placeholder, ImageView imageView) {
        mStrategy.loadGifImage(url, placeholder, imageView);
    }

    public void loadCircleImage(String url, int placeholder, ImageView imageView) {
        mStrategy.loadCircleImage(url,placeholder,imageView);
    }

    public void loadCircleBorderImage(String url, int placeholder, ImageView imageView, float borderWidth, int borderColor) {
        mStrategy.loadCircleBorderImage(url, placeholder, imageView, borderWidth, borderColor);
    }

    public void loadCircleBorderImage(String url, int placeholder, ImageView imageView, float borderWidth, int borderColor, int heightPX,int widthPX) {
        mStrategy.loadCircleBorderImage(url, placeholder, imageView, borderWidth, borderColor, heightPX,widthPX);
    }

    public void loadImage(String url, ImageView imageView) {
        mStrategy.loadImage(url, imageView);
    }

    public void loadImageWithAppCxt(String url, ImageView imageView) {
        mStrategy.loadImageWithAppCxt(url,imageView);
    }

    public void loadImageWithProgress(String url, ImageView imageView, ProgressLoadListener listener) {
        mStrategy.loadImageWithProgress(url,imageView,listener);
    }

    public void loadGifWithPrepareCall(String url, ImageView imageView, SourceReadyListener listener) {
        mStrategy.loadGifWithPrepareCall(url,imageView,listener);
    }

    public void loadImageWithPrepareCall(String url, ImageView imageView,int placeholder, SourceReadyListener listener) {
        mStrategy.loadImageWithPrepareCall(url, imageView, placeholder, listener);
    }

    /**
     * 策略模式的注入操作
     *
     * @param strategy
     */
    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        mStrategy = strategy;
    }

    /**
     * 需要展示图片加载进度的请参考 GalleryAdapter
     * 样例如下所示
     */

    /**
     * 清除图片磁盘缓存
     */
    public void clearImageDiskCache(final Context context) {
        mStrategy.clearImageDiskCache(context);
    }

    /**
     * 清除图片内存缓存
     */
    public void clearImageMemoryCache(Context context) {
        mStrategy.clearImageMemoryCache(context);
    }

    /**
     * 根据不同的内存状态，来响应不同的内存释放策略
     *
     * @param context
     * @param level
     */
    public void trimMemory(Context context, int level) {
        mStrategy.trimMemory(context, level);
    }

    /**
     * 清除图片所有缓存
     */
    public void clearImageAllCache(Context context) {
        clearImageDiskCache(context.getApplicationContext());
        clearImageMemoryCache(context.getApplicationContext());
    }

    /**
     * 获取缓存大小
     *
     * @return CacheSize
     */
    public String getCacheSize(Context context) {
        return mStrategy.getCacheSize(context);
    }

    public void saveImage(Context context, String url, String savePath, String saveFileName, ImageSaveListener listener) {
        mStrategy.saveImage(context, url, savePath, saveFileName, listener);
    }


}