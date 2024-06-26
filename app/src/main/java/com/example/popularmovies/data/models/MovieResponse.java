package com.example.popularmovies.data.models;

import java.util.List;

public class MovieResponse {
    private int page;
    private List<Movie> results;


    public MovieResponse(int page, List<Movie> results) {
        this.page = page;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
