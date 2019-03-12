package com.example.ratemaplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ratemaplate.adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class MatchesActivity extends AppCompatActivity {

    // for debugging
    private static final String TAG = "matches_Activity";

    // variables
    //passing the data to the adapters
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matches_activity);
        // debugging
        Log.d(TAG, "onCreate: started");

        initImageBitmaps();
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImagesBitmaps: preparing bitmaps");

        //plate images from google
        //Plate name
        mImageUrls.add(R.drawable.cheeseontoast);
        mNames.add("Cottage Pie, Beans & Ketchup");

        mImageUrls.add(R.drawable.sosij);
        mNames.add("Eggs and Ham");

        mImageUrls.add(R.drawable.cheeseontoast);
        mNames.add("image one");

        mImageUrls.add(R.drawable.cheeseontoast);
        mNames.add("image one");

        mImageUrls.add(R.drawable.cheeseontoast);
        mNames.add("image one");

        mImageUrls.add(R.drawable.cheeseontoast);
        mNames.add("image one");

        mImageUrls.add(R.drawable.cheeseontoast);
        mNames.add("image one");

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init RecyclerView");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}

