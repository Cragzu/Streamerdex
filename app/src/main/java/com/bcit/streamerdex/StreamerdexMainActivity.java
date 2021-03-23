package com.bcit.streamerdex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StreamerdexMainActivity extends AppCompatActivity {

    DatabaseReference databaseStreams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streamerdex_main);

        databaseStreams = FirebaseDatabase.getInstance().getReference("streamerdex");

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