package com.android.base;


import android.util.Log;

import com.alphabet.app.base.BaseActivity;
import com.alphabet.app.http.HttpClient;
import com.alphabet.app.http.Method;
import com.alphabet.app.http.rx.NetResquestSubscriber;
import com.alphabet.app.http.rx.SubscriberOnNextListener;

public class MainActivity extends BaseActivity {
    

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        HttpClient.getInstance().Api().send(new HttpClient.Builder()
                        .url("getParam")
                        .add("key1","key1")
                        .header("hd1","header2")
                        .method(Method.GET)
                        .build(), 
        new NetResquestSubscriber<Object>(new SubscriberOnNextListener<Object>() {
            @Override
            public void onNext(Object o) {
                Log.i("Http",o.toString());
            }
        }));
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }
}
