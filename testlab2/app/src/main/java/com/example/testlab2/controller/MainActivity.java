package com.example.testlab2.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testlab2.R;
import com.example.testlab2.model.MusicTrack;
import com.example.testlab2.model.MyMusicCollection;

public class MainActivity extends AppCompatActivity {

    //declare
    private MyMusicCollection tracks;
    private ListView myListView;
    private ArrayAdapter myArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set tracks to be a new object of type MyMusicCollection
        tracks = new MyMusicCollection();

        //store the data fields objects
        myListView = (ListView) findViewById(R.id.listView);

        //array adapter, list view

    }

    //question 3
    public void addMusicTrack(View view) {
        //takes user input
        EditText trackNameView = findViewById(R.id.trackName);
        EditText artistNameView = findViewById(R.id.artistName);
        EditText runtimeView = findViewById(R.id.runtime);

        //error checking
        if(trackNameView.getText().length() == 0 || artistNameView.getText().length() == 0 || runtimeView.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter track details", Toast.LENGTH_SHORT).show();
            return;
        }

        //user input into strings
        String trackName = trackNameView.getText().toString();
        String artistName = artistNameView.getText().toString();
        Double runtime;

        try {
            runtime = Double.parseDouble(runtimeView.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Please enter a number for the runtime", Toast.LENGTH_SHORT).show();
            return;
        }

    //add music track
    MusicTrack track = new MusicTrack(trackName, artistName, runtime);
        if(tracks.getMusicTracks().contains(track)) {
            Toast.makeText(getApplicationContext(), "This track is already in your collection", Toast.LENGTH_SHORT).show();
        } else {
            tracks.addTracks(track);
            Toast.makeText(getApplicationContext(), "Enter track details", Toast.LENGTH_SHORT).show();
        }

        //clear inout box

        myArrayAdapter.notifyDataSetChanged();
    }

}
