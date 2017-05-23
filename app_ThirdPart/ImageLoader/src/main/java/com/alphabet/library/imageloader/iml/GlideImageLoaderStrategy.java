package com.alphabet.library.imageloader.iml;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;

import com.alphabet.core.utils.FileUtils;
import com.alphabet.core.utils.ImageUtils;
import com.alphabet.library.imageloader.BaseImageLoaderStrategy;
import com.alphabet.library.imageloader.listener.ImageSaveListener;
import com.alphabet.library.imageloader.listener.ProgressLoadListener;
import com.alphabet.library.imageloader.listener.SourceReadyListener;
import com.alphabet.library.imageloader.transformation.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

/**
 * Created by Arison on 2017/5/22.
 * 用GlideImageLoader加载图片
 */
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {
    @Override
    public void loadImage(String url, ImageView imageView) {
        Glide.with(imageView.getContext()).load(url)
                .placeholder(imageView.getDrawable())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    @Override
    public void loadImageWithAppCxt(String url, ImageView imageView) {
        Glide.with(imageView.getContext().getApplicationContext()).load(url)
                .placeholder(imageView.getDrawable())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    @Override
    public void loadImage(String url, int placeholder, ImageView imageView) {
        loadNormal(imageView.getContext(), url, placeholder, imageView);
    }

    @Override
    public void loadImage(Context context, String url, int placeholder, ImageView imageView) {
        loadNormal(context, url, placeholder, imageView);
    }

    @Override
    public void loadCircleImage(String url, int placeholder, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(placeholder).dontAnimate()
                .transform(new GlideCircleTransform(imageView.getContext()))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    @Override
    public void loadCircleBorderImage(String url, int placeholder, ImageView imageView, float borderWidth, int borderColor) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(placeholder)
                .dontAnimate()
                .transform(new GlideCircleTransform(imageView.getContext(), borderWidth, borderColor))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    @Override
    public void loadCircleBorderImage(String url, int placeholder, ImageView imageView, float borderWidth, int borderColor, int heightPx, int widthPx) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(placeholder)
                .dontAnimate()
                .transform(new GlideCircleTransform(imageView.getContext(), borderWidth, borderColor, heightPx, widthPx))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    @Override
    public void loadGifImage(String url, int placeholder, ImageView imageView) {
        loadGif(imageView.getContext(), url, placeholder, imageView);
    }

    /**
      * @desc:图片加载进度 暂未实现
      * @author：Arison on 2017/5/23
      */
    @Override
    public void loadImageWithProgress(String url, ImageView imageView, ProgressLoadListener listener) {

    }

    @Override
    public void loadImageWithPrepareCall(String url, ImageView imageView, int placeholder, final SourceReadyListener listener) {
        Glide.with(imageView.getContext()).load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(placeholder)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        listener.onResourceReady(resource.getIntrinsicWidth(), resource.getIntrinsicHeight());
                        return false;
                    }
                }).into(imageView);
    }

    @Override
    public void loadGifWithPrepareCall(String url, ImageView imageView, final SourceReadyListener listener) {
        Glide.with(imageView.getContext()).load(url).asGif()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).
                listener(new RequestListener<String, GifDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, String model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        listener.onResourceReady(resource.getIntrinsicWidth(), resource.getIntrinsicHeight());
                        return false;
                    }
                }).into(imageView);
    }

    @Override
    public void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context.getApplicationContext()).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context.getApplicationContext()).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                //只能在主线程，即UI线程执行
                Glide.get(context.getApplicationContext()).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void trimMemory(Context context, int level) {
        Glide.get(context).trimMemory(level);
    }

    /**
     * @desc:获取缓存文件的大小
     * @author：Arison on 2017/5/23
     */
    @Override
    public String getCacheSize(Context context) {
        try {
            return FileUtils.getFormatSize(FileUtils.getFolderSize(Glide.getPhotoCacheDir(context.getApplicationContext())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveImage(Context context, String url, String savePath, String saveFileName, ImageSaveListener listener) {
        if (!FileUtils.isSDCardExsit() || TextUtils.isEmpty(url)) {
            listener.onSaveFail();
            return;
        }
        InputStream fromStream=null;
        OutputStream toStream=null;
        try {
            File cacheFile=Glide.with(context).load(url).downloadOnly(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL).get();
            if (cacheFile == null || !cacheFile.exists()) {
                listener.onSaveFail();
                return;
            }

            File dir = new File(savePath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            File file = new File(dir, saveFileName + ImageUtils.getPicType(cacheFile.getAbsolutePath()));
            fromStream = new FileInputStream(cacheFile);
            toStream = new FileOutputStream(file);
            byte length[] = new byte[1024];
            int count;
            while ((count = fromStream.read(length)) > 0) {
                toStream.write(length, 0, count);
            }
            //用广播通知相册进行更新相册
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);
            listener.onSaveSuccess();

        } catch (InterruptedException e) {
            e.printStackTrace();
            listener.onSaveFail();
        } catch (ExecutionException e) {
            e.printStackTrace();
            listener.onSaveFail();
        } catch (Exception e){
            e.printStackTrace();
            listener.onSaveFail();
        }finally {
            if (fromStream != null) {
                try {
                    fromStream.close();
                    toStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    fromStream = null;
                    toStream = null;
                }
            }
        }

    }


    /**
     * load image with Glide
     */
    private void loadNormal(final Context ctx, final String url, int placeholder, ImageView imageView) {
        Glide.with(ctx)
                .load(url)
                .placeholder(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }


    /**
     * load image with Glide
     */
    private void loadGif(final Context ctx, String url, int placeholder, ImageView imageView) {
        Glide.with(ctx).load(url)
                .asGif()
                .placeholder(placeholder)
                .skipMemoryCache(true)//禁止内存缓存：
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GifDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, String model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                }).into(imageView);
    }
}
