package com.example.ratemaplate;

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

import com.example.ratemaplate.adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class MatchesActivity extends AppCompatActivity {

    // for debugging
    private static final String TAG = "matches_Activity";

    // variables
    //passing the data to the adapters
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private RecyclerViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matches_activity);
        // debugging
        Log.d(TAG, "onCreate: started");

        initImageBitmaps();

        Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImagesBitmaps: preparing bitmaps");

        //plate images from google
        //Plate name
        mImageUrls.add(R.drawable.cheeseontoast);
        mNames.add("Cheese on Toast");

        mImageUrls.add(R.drawable.sosij);
        mNames.add("Fresh Sausages");

        mImageUrls.add(R.drawable.chicken);
        mNames.add("Medium Rare Chicken Dinner");

        mImageUrls.add(R.drawable.chickendinos);
        mNames.add("Chicken Dinosaurs and Curry Sauce");

        mImageUrls.add(R.drawable.hameggs);
        mNames.add("Ham and Eggs");

        mImageUrls.add(R.drawable.cheeseontoast);
        mNames.add("image one");

        mImageUrls.add(R.drawable.cheeseontoast);
        mNames.add("image one");

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init RecyclerView");
        adapter = new RecyclerViewAdapter(mNames, mImageUrls, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    // search bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, adapter.getFilter().toString());


                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}


