package com.twu.biblioteca.entity;

public class Movie {
    private int id;
    private String name;
    private int year;
    private String director;
    private int rating;

    public Movie() {
    }

    public Movie(int id, String name, int year, String director, int rating) {

        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }
}
