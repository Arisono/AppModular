package com.alphabet.core.utils;

import android.graphics.BitmapFactory;
import android.text.TextUtils;

/**
 * 图像处理工具类
 * 
 * Created by Arison on 2017/5/23.
 */
public class ImageUtils {

    
    
    public static String getPicType(String pathName) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        String type = options.outMimeType;
        if (!TextUtils.isEmpty(type)) {
            try {
                type = type.substring(6, type.length());
                if ("gif".equals(type)) {
                    return ".gif";
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return ".jpg";
    }
}
