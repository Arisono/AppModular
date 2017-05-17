package com.alphabet.app.http.rx;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by Arison on 2017/5/15.
 */
public class RxJavaUtils {

    public static Scheduler getScheduler(final String name) {
       return Schedulers.from(Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable,name);
            }
        }));
       // return Schedulers.from(Executors.newCachedThreadPool(r -> new Thread(r,name)));
    }
}
