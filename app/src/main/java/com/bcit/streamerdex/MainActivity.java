package com.bcit.streamerdex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    TestFragment testFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        testFragment = (TestFragment) fragmentManager.findFragmentById(R.id.fragment_main1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        testFragment.UpdateButton("Sparrow");
    }
}