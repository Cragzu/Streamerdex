package com.bcit.streamerdex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StreamerdexMainActivity extends AppCompatActivity {

    DatabaseReference databaseStreams;
    ArrayList<Stream> listOfStreams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseStreams = FirebaseDatabase.getInstance().getReference("streamerdex/streamers");
        listOfStreams = new ArrayList<>();

        databaseStreams.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (int index = 0; index < snapshot.getChildrenCount(); index++) {
                    Stream stream = new Stream(
                            ((String)((HashMap)((ArrayList) snapshot.getValue()).get(index)).get("streamerName")),
                            ((String)((HashMap)((ArrayList) snapshot.getValue()).get(index)).get("streamTitle")),
                            ((String)((HashMap)((ArrayList) snapshot.getValue()).get(index)).get("streamLink")),
                            ((String)((HashMap)((ArrayList) snapshot.getValue()).get(index)).get("streamDescription")),
                            ((String)((HashMap)((ArrayList) snapshot.getValue()).get(index)).get("videoLink")),
                            ((ArrayList)((HashMap)((ArrayList) snapshot.getValue()).get(index)).get("tags"))
                    );
                    listOfStreams.add(stream);
                }
                //System.out.println(listOfStreams);

                setContentView(R.layout.activity_streamerdex_main);

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("listOfStreams", listOfStreams);
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment);
                fragment.setArguments(bundle);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

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