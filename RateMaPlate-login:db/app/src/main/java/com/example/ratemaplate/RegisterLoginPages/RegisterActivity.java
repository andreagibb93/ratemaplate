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
import com.example.ratemaplate.R;
import com.example.ratemaplate.dashboard;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    //data fields
    EditText usernameView, emailView, passwordView, pwconfirmView;
    //submit register button
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //assign data fields with user input
        usernameView = (EditText) findViewById(R.id.username);
        emailView = (EditText) findViewById(R.id.email);
        passwordView = (EditText) findViewById(R.id.pass);
        pwconfirmView = (EditText) findViewById(R.id.cpass);

        //register button
        registerBtn = (Button) findViewById(R.id.register);

        //register button listener
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //convert user input to strings
                String username = usernameView.getText().toString();
                String email = emailView.getText().toString();
                String password = passwordView.getText().toString();
                String confirmPassword = pwconfirmView.getText().toString();

                //user alert, fields are empty
                if(username.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // s3 & s4 checks both passwords match in both input fields
                if(!(password.equals(confirmPassword))) {
                    Toast.makeText(getApplicationContext(),"Passwords don't match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Make a new request to the server
                RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
                StringRequest sr = new StringRequest(Request.Method.GET,
                        "http://10.0.2.2:8080/create_user?username=" + username + "&email=" + email + "&password=" + password,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject object = new JSONObject(response);
                                    Toast.makeText(getApplicationContext(), object.getString("response"), Toast.LENGTH_SHORT).show();
                                    if (!object.getBoolean("error")) {
                                        Intent intent = new Intent(getApplicationContext(), com.example.ratemaplate.dashboard.class);
                                        startActivity(intent);
                                    }
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


        // user can be made mulitple times
        // make the email their primary key
    }
}