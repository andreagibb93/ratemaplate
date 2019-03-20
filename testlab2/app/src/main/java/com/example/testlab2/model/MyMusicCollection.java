package com.example.testlab2.model;

import android.os.Debug;
import android.util.Log;

import java.util.ArrayList;

public class MyMusicCollection {
    public static final String DEBUG_TAG = "MyMusicCollection";

    //question 2
    //declare arraylist
    public ArrayList<MusicTrack> collection;

    //constructor
    public MyMusicCollection() { collection = new ArrayList<>();
    }

    //get 'return' value method
    public ArrayList<MusicTrack> getMusicTracks() {
        return this.collection;
    }

    //add the parameters
    public void addTracks(MusicTrack track) {
        if(this.collection.contains(track)) {
            Log.d(DEBUG_TAG, "The music collection already contains this track");
        } else {
            collection.add(track);
            Log.d(DEBUG_TAG, "Track added to the collection");
        }

        }
    }
