package com.android.base;


import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.alphabet.app.base.BaseActivity;
import com.alphabet.app.http.HttpClient;
import com.alphabet.app.http.Method;
import com.alphabet.app.http.rx.Result1Listener;
import com.alphabet.app.http.rx.ResultSubscriber;
import com.alphabet.core.utils.FileUtils;
import com.alphabet.message.ToastUtils;
import com.orhanobut.logger.Logger;
import com.tapadoo.alerter.Alerter;
import com.tapadoo.alerter.OnHideAlertListener;
import com.tapadoo.alerter.OnShowAlertListener;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


//    @BindView(R.id.iv_normal)
//    ImageView ivNormal;
//    @BindView(R.id.iv_gif)
//    ImageView ivGif;
//    @BindView(R.id.iv_circle)
//    ImageView ivCircle;
//    @BindView(R.id.iv_circle1)
//    ImageView ivCircle1;
//    @BindView(R.id.rl_content)
//    RelativeLayout rl_content;
    
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_example;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        
      
        testHttpApi();

        findViewById(R.id.btnAlertDefault).setOnClickListener(this);
        findViewById(R.id.btnAlertColoured).setOnClickListener(this);
        findViewById(R.id.btnAlertCustomIcon).setOnClickListener(this);
        findViewById(R.id.btnAlertTextOnly).setOnClickListener(this);
        findViewById(R.id.btnAlertOnClick).setOnClickListener(this);
        findViewById(R.id.btnAlertVerbose).setOnClickListener(this);
        findViewById(R.id.btnAlertCallback).setOnClickListener(this);
        findViewById(R.id.btnAlertInfiniteDuration).setOnClickListener(this);
       // testImageLoader();
    }


    @Override
    protected void onResume() {
        super.onResume();
        testMessage();
    }

    private void testMessage() {
//        ToastUtils.create().Builder().showAtTop("显示在顶部！");
//        ToastUtils.create().Builder().showAtCenter("显示在中央！");
//        ToastUtils.create().Builder().showAtBottom("显示在底部");
        //ToastUtils.create().Builder().showAtNotiftion("显示在通知栏");
        
       new android.os.Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               ToastUtils.create().Builder().showAtNotiftion(MainActivity.this,"显示在顶部");
           }
       },10);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

//    private void testImageLoader() {
//        ImageLoaderUtil.getInstance()
//                .loadImage("http://image.sports.baofeng.com/25a3dbb0c99c5e48e52e60941ed230be",
//                        R.drawable.loading,
//                        ivNormal);
//        ImageLoaderUtil.getInstance()
//                .loadImage("http://image.sports.baofeng.com/19ce5d6ac3b4fff255196f200b1d3079",
//                        R.drawable.loading,
//                        ivGif);
//        ImageLoaderUtil.getInstance()
//                .loadCircleBorderImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495447672149&di=3465748a572ffec42f514cc17067075d&imgtype=0&src=http%3A%2F%2Fwww.sxrczx.com%2Fimgs%2Favatarcsdnnet%2Fblogpic%2F20150619190542756.jpg",
//                        R.drawable.loading,
//                        ivCircle, 2,
//                        this.getResources().getColor(R.color.colorAccent),
//                        ScreenUtils.dip2px(this, 200),
//                        ScreenUtils.dip2px(this, 200));
//        ImageLoaderUtil.getInstance()
//                .loadCircleImage("http://image.sports.baofeng.com/25a3dbb0c99c5e48e52e60941ed230be",
//                        R.drawable.loading,
//                        ivCircle1);
//        ImageLoaderUtil.getInstance().loadImage("http://img.blog.csdn.net/20161202172529289",
//                R.drawable.loading, ivNormal);
//    }

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



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAlertColoured: {
               showAlertColoured();
                //ToastUtils.create().Builder().showAtNotiftion(MainActivity.this,"显示在顶部");
                break;
            }
            case R.id.btnAlertCustomIcon: {
                showAlertWithIcon();
                break;
            }
            case R.id.btnAlertTextOnly: {
                showAlertTextOnly();
                break;
            }
            case R.id.btnAlertOnClick: {
                showAlertWithOnClick();
                break;
            }
            case R.id.btnAlertVerbose: {
                showAlertVerbose();
                break;
            }
            case R.id.btnAlertCallback: {
                showAlertCallbacks();
                break;
            }
            case R.id.btnAlertInfiniteDuration: {
                showAlertInfiniteDuration();
            }
            default: {
                showAlertDefault();
            }
        }
    }

    private void showAlertDefault() {
        Alerter.create(MainActivity.this)
                .setTitle("Alert Title")
                .setText("Alert text...")
               // .disableOutsideTouch()
                .show();
    }

    private void showAlertColoured() {
        Alerter.create(MainActivity.this)
                .setTitle("Alert Title")
                .setText("Alert text...")
                .setBackgroundColor(R.color.colorAccent)
                .show();
    }

    private void showAlertWithIcon() {
        Alerter.create(MainActivity.this)
                .setText("Alert text...")
                .setIcon(R.drawable.alerter_ic_face)
                .show();
    }

    private void showAlertTextOnly() {
        Alerter.create(MainActivity.this)
                .setText("Alert text...")
                .show();
    }

    private void showAlertWithOnClick() {
        Alerter.create(MainActivity.this)
                .setTitle("Alert Title")
                .setText("Alert text...")
                .setDuration(10000)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "OnClick Called", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    private void showAlertVerbose() {
        Alerter.create(MainActivity.this)
                .setTitle("Alert Title")
                .setText("The alert scales to accommodate larger bodies of text. " +
                        "The alert scales to accommodate larger bodies of text. " +
                        "The alert scales to accommodate larger bodies of text.")
                .show();
    }

    private void showAlertCallbacks(){
        Alerter.create(MainActivity.this)
                .setTitle("Alert Title")
                .setText("Alert text...")
                .setDuration(10000)
                .setOnShowListener(new OnShowAlertListener() {
                    @Override
                    public void onShow() {
                        Toast.makeText(MainActivity.this, "Show Alert", Toast.LENGTH_LONG).show();
                    }
                })
                .setOnHideListener(new OnHideAlertListener() {
                    @Override
                    public void onHide() {
                        Toast.makeText(MainActivity.this, "Hide Alert", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    private void showAlertInfiniteDuration() {
        Alerter.create(MainActivity.this)
                .setTitle("Alert Title")
                .setText("Alert text...")
                .enableInfiniteDuration(true)
                .show();
    }
}
