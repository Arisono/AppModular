package com.android.base;


import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alphabet.app.base.BaseActivity;
import com.alphabet.app.http.HttpClient;
import com.alphabet.app.http.Method;
import com.alphabet.app.http.rx.Result1Listener;
import com.alphabet.app.http.rx.ResultSubscriber;
import com.alphabet.core.utils.FileUtils;
import com.alphabet.core.utils.ScreenUtils;
import com.alphabet.library.imageloader.ImageLoaderUtil;
import com.alphabet.message.ToastUtils;
import com.orhanobut.logger.Logger;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.iv_normal)
    ImageView ivNormal;
    @BindView(R.id.iv_gif)
    ImageView ivGif;
    @BindView(R.id.iv_circle)
    ImageView ivCircle;
    @BindView(R.id.iv_circle1)
    ImageView ivCircle1;
    @BindView(R.id.rl_content)
    RelativeLayout rl_content;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        
        testMessage();
      
        testHttpApi();
        testImageLoader();
    }

    private void testMessage() {

        ToastUtils.create().Builder().show("系统api消息提醒");
        

        //ToastUtils.create().Builder().showAtBottom(this,"你点击我啊！");
        
        
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void testImageLoader() {
        ImageLoaderUtil.getInstance()
                .loadImage("http://image.sports.baofeng.com/25a3dbb0c99c5e48e52e60941ed230be",
                        R.drawable.loading,
                        ivNormal);
        ImageLoaderUtil.getInstance()
                .loadImage("http://image.sports.baofeng.com/19ce5d6ac3b4fff255196f200b1d3079",
                        R.drawable.loading,
                        ivGif);
        ImageLoaderUtil.getInstance()
                .loadCircleBorderImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495447672149&di=3465748a572ffec42f514cc17067075d&imgtype=0&src=http%3A%2F%2Fwww.sxrczx.com%2Fimgs%2Favatarcsdnnet%2Fblogpic%2F20150619190542756.jpg",
                        R.drawable.loading,
                        ivCircle, 2,
                        this.getResources().getColor(R.color.colorAccent),
                        ScreenUtils.dip2px(this, 200),
                        ScreenUtils.dip2px(this, 200));
        ImageLoaderUtil.getInstance()
                .loadCircleImage("http://image.sports.baofeng.com/25a3dbb0c99c5e48e52e60941ed230be",
                        R.drawable.loading,
                        ivCircle1);
        ImageLoaderUtil.getInstance().loadImage("http://img.blog.csdn.net/20161202172529289",
                R.drawable.loading, ivNormal);
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
