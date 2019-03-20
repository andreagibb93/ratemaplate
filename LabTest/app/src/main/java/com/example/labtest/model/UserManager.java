package com.example.labtest.model;

import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> collection;

    public UserManager() {
        this.collection = new ArrayList<>();
    }

    public ArrayList<User> getCollection() {
        return collection;
    }

    public void addUser(User user) {
        collection.add(user);
    }
}
