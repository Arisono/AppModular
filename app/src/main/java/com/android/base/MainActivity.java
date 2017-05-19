package com.android.base;


import com.alphabet.app.base.BaseActivity;
import com.alphabet.app.http.HttpClient;
import com.alphabet.app.http.Method;
import com.alphabet.app.http.rx.Result1Listener;
import com.alphabet.app.http.rx.ResultSubscriber;
import com.alphabet.app.utils.FileUtils;
import com.orhanobut.logger.Logger;

import java.io.File;

public class MainActivity extends BaseActivity {
    

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        testHttpApi();
    }

    private void testHttpApi() {
        //get post方法测试
        HttpClient.getInstance().Api().send(new HttpClient.Builder()
                        .url("postParam")//exception01//json
                        .add("key1", "key1")
                        .add("username", "arison")
                        .add("password", "111111")
                        .header("hd1", "header2")
                        .method(Method.POST)
                        .build(),
                new ResultSubscriber<>(new Result1Listener<Object>() {

                    @Override
                    public void onResponse(Object o) {
                        Logger.d(o);
                    }
                }
                ));
        //上传文件
        HttpClient.getInstance().Api().uploads(new HttpClient.Builder()
                .url("uploadFiles")
                .add("key", new File(FileUtils.getSDRoot() + "/uu/chat/chatImage.png"))
                .add("key1", new File(FileUtils.getSDRoot() + "/uu/chat/head4.png"))
                .build(),
                new ResultSubscriber<>(new Result1Listener<Object>() {
                    @Override
                    public void onResponse(Object o) {
                        Logger.d(o);
                    }
                })
        );
        
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }
}
