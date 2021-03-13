package com.bcit.streamerdex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class StreamerdexMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streamerdex_main);

//        BM - Code for getting stream
//        String url = "https://iblake.netlify.app/streamerdex/codemiko";
//
//        WebView mWebView = (WebView) findViewById(R.id.stream_view);
//        mWebView.setInitialScale(1);
//        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);
//        mWebView.loadUrl(url);

    }
}