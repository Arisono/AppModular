package com.android.base;

import com.alphabet.app.base.BaseApplication;
import com.alphabet.app.http.HttpClient;
import com.alphabet.app.http.cache.CacheType;
import com.alphabet.app.http.impl.RetrofitImpl;

/**
 * Created by Arison on 2017/5/15.
 */
public class RxApplication extends BaseApplication {

    public static final String BASE_URL="http://192.168.253.200:8080/Chapter/";

   
    public void init() {
        new HttpClient.Builder(BASE_URL)//根路径
                .add("CommonParam1", "公共请求参数1")//公共参数  //局部可累加
                .add("CommonParam2", "公共请求参数2")
                .header("Cookie", "abdclejdldj82jk23jfjd")//全局请求头   //局部可累加
                .maxRetryCount(0)//局部可覆盖
                .isDebug(true)//局部可覆盖
                .retryTimeout(1000)//局部可覆盖
                .cacheFile(getInstance().getCacheDir())//局部可覆盖
                .cacheFileSize(10240*1024)//局部可覆盖
                .cacheType(CacheType.ONLY_NETWORK)//局部可覆盖
                .cacheTime(60*200)//设置10分钟 //局部可覆盖
                .connectTimeout(10)//局部可覆盖
                .readTimeout(10)//局部可覆盖
                .writeTimeout(10)//局部可覆盖
                .httpBase(RetrofitImpl.getInstance())//局部可覆盖
                .build(true);//保持单例
    }

    @Override
    public void initImageClient() {
        super.initImageClient();
       
    }

    @Override
    public void initLogHelper() {
        super.initLogHelper();
     
    }
}
