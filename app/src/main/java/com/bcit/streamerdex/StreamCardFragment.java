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

import java.util.ArrayList;

public class StreamCardFragment extends Fragment {

    private TextView streamerName;
    private TextView streamName;
    private TextView description;
    private WebView streamView;
    private ArrayList<Stream> listOfStreams;

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

        Bundle bundle = this.getArguments();
        System.out.println(bundle);
        if (bundle != null) {
            listOfStreams = bundle.getParcelableArrayList("listOfStreams");
            System.out.println(listOfStreams);
        }

        streamerName = view.findViewById(R.id.textView_StreamerName);
        streamName = view.findViewById(R.id.textView_StreamName);
        description = view.findViewById(R.id.textView_StreamDesc);
        streamView = view.findViewById(R.id.stream_view);


        // BM - Code for getting stream
        String url = "https://iblake.netlify.app/streamerdex/dasmehdi";

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
