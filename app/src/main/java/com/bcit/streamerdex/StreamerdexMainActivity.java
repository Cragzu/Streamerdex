package com.bcit.streamerdex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class StreamerdexMainActivity extends AppCompatActivity {

    DatabaseReference databaseStreams;
    ArrayList<Stream> listOfStreams;
    RecyclerView rvStreams;
    ArrayList<String> tagPreferences;
    StreamCardAdapter streamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streamerdex_main);

        databaseStreams = FirebaseDatabase.getInstance().getReference("streamerdex/streamers");
        listOfStreams = new ArrayList<>();
        rvStreams = findViewById(R.id.main_activity_recyclerView);
    }

    @Override
    protected void onStart() {
        Intent intent = getIntent();
        tagPreferences = intent.getStringArrayListExtra("tagPreferences");

        databaseStreams.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    listOfStreams.add(postSnapshot.getValue(Stream.class));
                }

                streamAdapter = new StreamCardAdapter(filterPreferences());
                rvStreams.setAdapter(streamAdapter);
                rvStreams.setLayoutManager(new LinearLayoutManager(StreamerdexMainActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });
        super.onStart();
    }

    // filters listOfStreams based on selected tags from Preferences Activity
    private ArrayList<Stream> filterPreferences() {
        return listOfStreams
                .stream()
                .filter(stream -> stream.getTags().stream().anyMatch(tagPreferences::contains))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
