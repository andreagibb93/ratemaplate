package com.example.ratemaplate.AdminPages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ratemaplate.R;
import com.example.ratemaplate.adapters.RecyclerViewAdapterAdmin;

import java.util.ArrayList;

public class AdminViewFlagged extends AppCompatActivity {

    // define a constant for TAGS
    private static final String TAG = "Flagged activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flagged_activity);

        //getIncomingIntent();
    }


    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents");

        //checks if there is intent, stops the app from crashing if there isnt anything to get
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")) {
            Log.d(TAG, "getIncomingIntent: found intent");

            // gets the image intent
            int imageUrl = getIntent().getIntExtra("image_url", 0);
            String imageName = getIntent().getStringExtra("image_name");
            Log.d("images", imageName + imageUrl);
            setImage(imageUrl, imageName);
        }
    }

    private void setImage(int imageUrl, String imageName) {
        Log.d(TAG,"setImage: setting the image and to widgets");

        // sets the text view
        TextView name = findViewById(R.id.userID);
        name.setText(imageName);

        // sets the text view
        TextView heading1 = findViewById(R.id.heading1);
        heading1.setText(imageName);

        // sets the text view
        TextView heading2 = findViewById(R.id.heading2);
        heading2.setText(imageName);

    }
}


