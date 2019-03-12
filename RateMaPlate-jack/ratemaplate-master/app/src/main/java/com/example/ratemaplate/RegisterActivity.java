package com.example.ratemaplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    //db helper class
    //data fields
    EditText e1,e2,e3,e4;
    //submit register button
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //assign data fields with user input
        e1 = (EditText) findViewById(R.id.username);
        e2 = (EditText) findViewById(R.id.email);
        e3 = (EditText) findViewById(R.id.pass);
        e4 = (EditText) findViewById(R.id.cpass);

        //register button
        b1 = (Button) findViewById(R.id.register);

        //register button listener
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //convert user input to strings
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();

                //user alert, fields are empty
                if(s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")) {
                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // s3 & s4 checks both passwords match in both input fields
                if(!(s3.equals(s4))) {
                    Toast.makeText(getApplicationContext(),"Passwords don't match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Make a new request to the server
                RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
                StringRequest sr = new StringRequest(Request.Method.GET,
                        "http://10.0.2.2:8080/create_user?username=" + s1 + "&email=" + s2 + "&password=" + s3,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject object = new JSONObject(response);
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


        // user can be made mulitple times
        // make the email their primary key
    }
}