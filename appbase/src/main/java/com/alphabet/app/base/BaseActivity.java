package com.alphabet.app.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 一般标准的activity基类封装
 * 1:沉浸栏设置问题
 * 2：
 * Created by Arison on 2017/5/3.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
   
    /*设置是否沉浸栏*/
    private boolean isSetStatusBar = false;
    /*设置是否全屏显示*/
    private boolean isFullScreen = false;
    /*设置是否禁止旋转屏幕*/
    /*是否输入日志信息*/
    protected boolean isDebug = true;
    protected  final String TAG =this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        $Log(TAG + "-->onCreate()");
        if (isSetStatusBar){
            setStatusBar();
        }
        if (isFullScreen){
            setAllowFullScreen(isFullScreen);
        }
        setContentView(getLayoutId());
        initView();
        setListener();
        initData();
    }

    /**
     * [沉浸状态栏]
     */
    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * [是否允许全屏]
     *
     * @param allowFullScreen
     */
    protected void setAllowFullScreen(boolean allowFullScreen) {
        if (allowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }
    }

    /**
     * 获取状态栏高度
     * @return
     */
/*    protected int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }*/

    /**
     * 设置ContentView
     * @return R.layout.xxx
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * add Listener
     */
    protected abstract void setListener();

    /**
     * 初始化数据
     */
    protected abstract void initData();
    
    
    @Override
    public void onClick(View view) {
        
    }


    @Override
    protected void onResume() {
        super.onResume();
        $Log(TAG + "-->onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        $Log(TAG + "-->onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        $Log(TAG + "-->onDestroy()");
    }

    // findViewById
 /*   public <T extends View> T $findViewById(int resId) {
        return (T) findViewById(resId);
    }*/

    // Toast
   /* protected void toast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }*/

    // Toast
   /* protected void toast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }*/

    
    // Log
    protected void $Log(String msg) {
        if (isDebug) {
            Log.d(this.getClass().getName(), msg);
        }
    }


    // startActivity
 /*   protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }*/

    // startActivity
   /* protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }*/

    // startActivityForResult
  /*  protected void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }*/

    // startActivityForResult
   /* protected void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }*/

    // getIntent
   /* protected Bundle getIntentExtra() {
        Intent intent = getIntent();
        Bundle bundle = null;
        if (null != intent)
            bundle = intent.getExtras();
        return bundle;
    }*/
}
