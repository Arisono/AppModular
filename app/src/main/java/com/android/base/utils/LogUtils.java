package com.android.base.utils;

import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
  * @desc:简易版的日志打印 
 *         类：行号：长log
  * @author：Arison on 2017/5/15
  */
public class LogUtils {
    private static final boolean DEBUG = true;

    public static void d(String TAG, String method, String msg) {
        Log.d(TAG, "[" + method + "]" + msg);
    }

    public static void d(String TAG, String msg) {
        if (DEBUG) {
            Log.d(TAG, "[" + getFileLineMethod() + "]" + msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            Log.d(_FILE_(), "[" + getLineMethod() + "]" + msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            Log.e(_FILE_(), getLineMethod() + msg);
        }
    }

    public static void e(String TAG, String msg) {
        if (DEBUG) {
            Log.e(TAG, getLineMethod() + msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            if (!TextUtils.isEmpty(msg))
                Log.d(_FILE_(), "[" + getFileLineMethod() + "]" + msg);
        }
    }

    public static String getFileLineMethod() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
        StringBuffer toStringBuffer = new StringBuffer("[")
                .append(traceElement.getFileName()).append(" | ")
                .append(traceElement.getLineNumber()).append(" | ")
                .append(traceElement.getMethodName()).append("]");
        return toStringBuffer.toString();
    }

    public static String getLineMethod() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
        StringBuffer toStringBuffer = new StringBuffer("[")
                .append(traceElement.getLineNumber()).append(" | ")
                .append(traceElement.getMethodName()).append("]");
        return toStringBuffer.toString();
    }

    public static String _FILE_() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
        return traceElement.getFileName();
    }

    public static String _FUNC_() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getMethodName();
    }

    public static int _LINE_() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getLineNumber();
    }

    public static String _TIME_() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(now);
    }

    public static void prinlnLongMsg(String TAG, String responseInfo) {
        if (responseInfo != null) {
            if (responseInfo.length() >= 3000) {
                Log.v(TAG, "sb.length = " + responseInfo.length());
                int chunkCount = responseInfo.length() / 3000;     // integer division
                for (int i = 0; i <= chunkCount; i++) {
                    int max = 3000 * (i + 1);
                    if (max >= responseInfo.length()) {
                        Log.v(TAG, "【chunk " + i + " of " + chunkCount + "】:" + responseInfo.substring(3000 * i));
                    } else {
                        Log.v(TAG, "【chunk " + i + " of " + chunkCount + "】:" + responseInfo.substring(3000 * i, max));
                    }
                }
            } else {
                Log.v(TAG, "sb.length = " + responseInfo.length());
                Log.v(TAG, responseInfo.toString());
            }
        }

    }
}
