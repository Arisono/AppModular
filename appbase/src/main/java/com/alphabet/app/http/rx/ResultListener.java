package com.alphabet.app.http.rx;

/**
 * Created by Arison on 2017/5/16.
 */
public interface ResultListener<T> {
    void onResponse(T t);
}
