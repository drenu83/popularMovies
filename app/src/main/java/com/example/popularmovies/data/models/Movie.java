package com.example.popularmovies.data.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey
    private int id;
    private String title;
    private String original_language;
    private String overview;
    private String poster_path;
    private double vote_average;
    private int vote_count;
    private double popularity;
    private String release_date;

    public Movie(int id, String title, String original_language, String overview, String poster_path, double vote_average, int vote_count, double popularity, String release_date) {
        this.id = id;
        this.title = title;
        this.original_language = original_language;
        this.overview = overview;
        this.poster_path = poster_path;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.popularity = popularity;
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}

