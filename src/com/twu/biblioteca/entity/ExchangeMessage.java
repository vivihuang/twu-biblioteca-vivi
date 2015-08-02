package com.twu.biblioteca.entity;

import java.util.List;

/**
 * Created by Vivi on 7/24/15.
 */
public class ExchangeMessage {

    private String outputMessage;
    private List<Book> bookList;
    private List<Movie> movieList;

    public ExchangeMessage() {
    }

    public ExchangeMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    public ExchangeMessage(String outputMessage, List<Book> bookList) {
        this.outputMessage = outputMessage;
        this.bookList = bookList;
    }

    public String getOutputMessage() {
        return outputMessage;
    }

    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }


    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
