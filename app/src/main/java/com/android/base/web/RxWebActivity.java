package com.android.base.web;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.android.base.R;

import im.delight.android.webview.AdvancedWebView;

public class RxWebActivity extends Activity implements AdvancedWebView.Listener{
    private AdvancedWebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_web);

        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.loadUrl("http://mall.ubtob.com");
    }

    @Override
    public void onPageStarted(String s, Bitmap bitmap) {
        
    }

    @Override
    public void onPageFinished(String s) {

    }

    @Override
    public void onPageError(int i, String s, String s1) {

    }

    @Override
    public void onDownloadRequested(String s, String s1, String s2, long l, String s3, String s4) {

    }

    @Override
    public void onExternalPageRequest(String s) {

    }
}
