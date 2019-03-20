package com.example.labtest.model;

public class User {
    private String name;
    private String id;
    private int age;

    public User() {
        this.name = "";
        this.id = "";
        this.age = 0;
    }

    public User(String name, String id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDetails() {
        return "Name: " + name + " id: " + id + " age: " + age;
    }
}