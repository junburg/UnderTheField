package com.example.gyeongsuk.underthefield.main;

/**
 * Created by Gyeongsuk on 2016-11-29.
 */
public class User {

    public String artist;
    public String title;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String artist, String title) {
        this.artist = artist;
        this.title = title;
    }
}