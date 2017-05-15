package com.alphabet.app.http.cache;

/**
 * Created by Arison on 2017/5/12.
 */
public class CacheType {
    public final static int ONLY_NETWORK = 0;//读取网络
    public final static int ONLY_CACHED = 1;//读取缓存
    public final static int CACHED_ELSE_NETWORK = 2;//先缓存后网络
    public final static int NETWORK_ELSE_CACHED = 3;//先网络后缓存
}
