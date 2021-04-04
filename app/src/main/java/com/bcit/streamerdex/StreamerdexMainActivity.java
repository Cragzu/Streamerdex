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

public class StreamerdexMainActivity extends AppCompatActivity {

    DatabaseReference databaseStreams;
    ArrayList<Stream> listOfStreams;
    RecyclerView rvStreams;

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
        databaseStreams.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    listOfStreams.add(postSnapshot.getValue(Stream.class));
                }
                // so listOfStreams now has a list of Stream objects
                // it seems redundant to get this snapshot then iterate through it and then
                // add to the stream, but from how the snapshot works, it won't bulk parse
                // .getValue(Stream.class)

                StreamCardAdapter adapter = new StreamCardAdapter(listOfStreams);
                rvStreams.setAdapter(adapter);
                rvStreams.setLayoutManager(new LinearLayoutManager(StreamerdexMainActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });
        super.onStart();
    }

}
