package com.example.ratemaplate.AdminPages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ratemaplate.R;
import com.example.ratemaplate.adapters.RecyclerViewAdapterAdmin;

import java.util.ArrayList;

public class admin_notifications extends AppCompatActivity {

        // for debugging
        private static final String TAG = "admin_notifications";

        // variables
        //passing the data to the adapters
        private ArrayList<String> flaggedContent = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_notifications);

            // debugging
            Log.d(TAG, "onCreate: started");
            initRecyclerView();
            initImageBitmaps();
        }

        private void initImageBitmaps() {
            Log.d(TAG, "initImagesBitmaps: preparing bitmaps");

            // list view of all flagged content

            flaggedContent.add("User reported inappropriate comment");

            flaggedContent.add("User reported inappropriate plate");

            flaggedContent.add("User reported inappropriate comment");

            flaggedContent.add("User reported inappropriate plate");

            flaggedContent.add("User reported inappropriate userID1238493");

            flaggedContent.add("User reported inappropriate plate");

            flaggedContent.add("User reported inappropriate comment");

            initRecyclerView();
        }

        private void initRecyclerView() {
            Log.d(TAG, "initRecyclerView: init RecyclerView");
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            RecyclerViewAdapterAdmin adapter = new RecyclerViewAdapterAdmin(flaggedContent);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }
    }
