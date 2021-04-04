package com.bcit.streamerdex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button button_Login;
    private Button button_Help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_Login = findViewById(R.id.button_Login);
        button_Help = findViewById(R.id.button_Help);

        button_Login.setOnClickListener(v -> goToPreferences());
        button_Help.setOnClickListener(v -> showHelpDialog());
    }

    private void goToPreferences() {
        Intent i = new Intent(this, PreferencesActivity.class);
        startActivity(i);
    }

    public void showHelpDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.help_popup_title);
        alert.setIcon(android.R.drawable.ic_menu_help);

        alert.setMessage(R.string.help_popup_body_1);

        alert.create().show();
    }

}