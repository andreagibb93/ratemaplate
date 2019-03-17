package com.example.ratemaplate.RegisterLoginPages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ratemaplate.AdminPages.admin_notifications;
import com.example.ratemaplate.MatchesActivity;
import com.example.ratemaplate.R;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    //edit text data fields
    EditText e1,e2;
    //user login button
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);



        //assign data fields with user input
        e1 = (EditText) findViewById(R.id.email);
        e2 = (EditText) findViewById(R.id.password);

        //login button
        b1 = (Button) findViewById(R.id.login);

        //register button listener
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //convert user input to strings
                String email = e1.getText().toString();
                String password = e2.getText().toString();

                //user alert, fields are empty
                if(email.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Make a new request to the server
                RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
                StringRequest sr = new StringRequest(Request.Method.GET,
                        "http://10.0.2.2:8080/login_user?email=" + email + "&password=" + password,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject object = new JSONObject(response);
                                    if (!object.getBoolean("error")) {
                                        Intent intent = new Intent(getApplicationContext(), MatchesActivity.class);
                                        startActivity(intent);
                                    }
                                    Toast.makeText(getApplicationContext(), object.getString("response"), Toast.LENGTH_SHORT).show();
                                } catch (Exception e) { }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

                //Add the request to the queue
                sr.setShouldCache(false);
                rq.add(sr);
            }
        });
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
        Intent intent = new Intent(this, MatchesActivity.class);
        startActivity(intent);
    }

    // short cut to admin user
    public void goToAdmin (View view) {
        Intent intent = new Intent(this, admin_notifications.class);
        startActivity(intent);
    }
}