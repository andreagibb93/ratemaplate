package com.example.ratemaplate.AdminPages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.example.ratemaplate.R;
import com.example.ratemaplate.adapters.RecyclerViewAdapterAdmin;

import java.util.ArrayList;

public class admin_notifications extends AppCompatActivity {

        // for debugging
        private static final String TAG = "admin_notifications";

    // variables
    //passing the data to the adapters
    private ArrayList<String> notifications = new ArrayList<>();
    private RecyclerViewAdapterAdmin adapter;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_notifications);

            // debugging
            Log.d(TAG, "onCreate: started");
            initRecyclerView();
            initNotifications();
        }

        private void initNotifications() {
            Log.d(TAG, "initImagesBitmaps: preparing bitmaps");

            // list view of all flagged content
            //
            notifications.add("User reported inappropriate comment");

            notifications.add("User reported inappropriate comment");

            notifications.add("User reported inappropriate comment ");
            initRecyclerView();
        }

        private void initRecyclerView() {
            Log.d(TAG, "initRecyclerView: init RecyclerView");
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            RecyclerViewAdapterAdmin adapter = new RecyclerViewAdapterAdmin(notifications, this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }
    }
