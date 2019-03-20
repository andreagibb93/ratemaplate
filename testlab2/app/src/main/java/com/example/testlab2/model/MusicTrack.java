package com.example.testlab2.model;

public class MusicTrack {

    //question 1
    //delcare the data fields
    private String trackName;
    private String artistName;
    private double runtime;

    public MusicTrack(String trackName, String artistName, double runtime) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.runtime = runtime;
    }

    // all gettters
    public String getTrackName() {
        return trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public double getRuntime() {
        return runtime;
    }

    //all setters
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setRuntime(double runtime) {
        this.runtime = runtime;
    }

    //gets all the details
    public void printDetails() {
        System.out.println(trackName);
        System.out.println(artistName);
        System.out.println(runtime);
        System.out.println("Details provided about this track");
    }

}
