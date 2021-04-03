package com.bcit.streamerdex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button button_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_Login = findViewById(R.id.button_Login);

        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPreferences();
            }
        });
    }

    private void goToPreferences() {
        Intent i = new Intent(this, PreferencesActivity.class);
        startActivity(i);
    }

}