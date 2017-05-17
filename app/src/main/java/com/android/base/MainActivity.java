package com.android.base;


import com.alphabet.app.base.BaseActivity;
import com.alphabet.app.http.HttpClient;
import com.alphabet.app.http.Method;
import com.alphabet.app.http.rx.Result1Listener;
import com.alphabet.app.http.rx.ResultSubscriber;
import com.orhanobut.logger.Logger;

public class MainActivity extends BaseActivity {
    

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        HttpClient.getInstance().Api().send(new HttpClient.Builder()
                        .url("postParam")//exception01//json
                        .add("key1", "key1")
                        .add("username","arison")
                        .add("password","111111")
                        .header("hd1","header2")
                        .method(Method.POST)
                        .build(), 
        new ResultSubscriber<>(new Result1Listener<Object>() {

            @Override
            public void onResponse(Object o) {
                Logger.d(o);
            }
        }
        ));
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }
}
