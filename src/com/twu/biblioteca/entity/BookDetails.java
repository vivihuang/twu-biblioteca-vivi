package com.twu.biblioteca.entity;

public class BookDetails {

    private int id;
    private String name;
    private String author;
    private int yearPublished;

    public BookDetails() {
    }

    public int getId() {
        return id;
    }

    public BookDetails(int id, String name, String author, int yearPublished) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }
}
