package com.alphabet.app.utils;

import android.os.Environment;

/**
 * Created by Arison on 2017/5/17.
 */
public class FileUtils {

    /**
     * 获取SD卡的根目录
     *
     * @return
     */
    public static String getSDRoot() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
