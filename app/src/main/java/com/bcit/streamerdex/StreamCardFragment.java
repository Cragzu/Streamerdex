package com.bcit.streamerdex;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StreamCardFragment extends Fragment {

    private Stream stream;
    private WebView streamView;

    public StreamCardFragment()
    {
        super(R.layout.fragment_stream_card);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Lifecycle fragment", "onCreate");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Lifecycle fragment", "onViewCreated");
        streamView = view.findViewById(R.id.stream_view);

        // BM - Code for getting stream
        String url = "https://iblake.netlify.app/streamerdex/codemiko";

        streamView.setInitialScale(1);
        WebSettings webSettings = streamView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        streamView.loadUrl(url);

        // JK - Code for filling out the fragment with Firebase Info

    }

    public void setStreamView(String videoLink) {
        StreamWebViewClient streamWebViewClient = new StreamWebViewClient();
        streamView.setWebViewClient(streamWebViewClient);
        streamView.loadUrl(videoLink);
    }

}
