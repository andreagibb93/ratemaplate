package com.example.ratemaplate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
    }

    // define a constant for TAGS
    private static final String TAG = "LoginActivity";

    // not already a member button leads to register screen
    public void goToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    // short cut to matches pages
    public void goToMatches(View view) {
        Intent intent = new Intent(this, matches_activity.class);
        startActivity(intent);
    }

    // short cut to admin user
    public void goToAdmin (View view) {
        Intent intent = new Intent(this, admin_notifications.class);
        startActivity(intent);
    }
}
