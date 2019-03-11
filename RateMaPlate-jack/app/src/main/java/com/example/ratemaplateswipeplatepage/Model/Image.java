package com.example.ratemaplateswipeplatepage.Model;

public class Image {
    private String title;
    private String description;


    public Image(String title, String description){
        this.title = title;
        this.description = description;
    }


    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

}
