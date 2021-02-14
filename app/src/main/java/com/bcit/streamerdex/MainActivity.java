package com.bcit.streamerdex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To start up PreferencesPage
        //Intent intent = new Intent(this, PreferencesPage.class);
        //startActivity(intent);
    }
}