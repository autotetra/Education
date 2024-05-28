package com.example.themusicplayerapp;

public class Song {
    private String title;
    private String artist;
    private String url;
    private String duration;

    public Song(String title, String artist, String url, String duration) {
        this.title = title;
        this.artist = artist;
        this.url = url;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getUrl() {
        return url;
    }

    public String getDuration() {
        return duration;
    }
}
