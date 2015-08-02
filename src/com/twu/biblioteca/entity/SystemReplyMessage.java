package com.twu.biblioteca.entity;

public class SystemReplyMessage {

    private String welcomeMessage = "Welcome to the Bangalore public library management system!";
    private String loginMessage = "Please use your library number and password to login!";
    private String loginAccountMessage = "Please input your library number and password separated with space to login.";
    private String invalidMenuMessage = "Select a valid option!";
    private String quitMessage = "Quit the system successful!";
    private String menuOptions = "List books.\nList Movies.";
    private String bookListMessage = "The book list:\n";
    private String successfulCheckoutBookMessage = "Thank you! Enjoy the book!";
    private String invalidCheckoutBookMessage = "That book is not available.";
    private String successfulReturnBookMessage = "Thank you for returning the book.";
    private String invalidReturnBookMessage = "That is not a valid book to return.";
    private String movieListMessage = "The movie list:\n";
    private String successfulCheckoutMovieMessage = "Thank you! Enjoy the movie!";
    private String invalidCheckoutMovieMessage = "That movie is not available.";
    private String successfulReturnMovieMessage = "Thank you for returning the movie.";
    private String invalidReturnMovieMessage = "That is not a valid movie to return.";

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public String getLoginAccountMessage() {
        return loginAccountMessage;
    }

    public String getInvalidMenuMessage() {
        return invalidMenuMessage;
    }

    public String getQuitMessage() {
        return quitMessage;
    }

    public String getMenuOptions() {
        return menuOptions;
    }

    public String getSuccessfulCheckoutBookMessage() {
        return successfulCheckoutBookMessage;
    }

    public String getInvalidCheckoutBookMessage() {
        return invalidCheckoutBookMessage;
    }

    public String getSuccessfulReturnBookMessage() {
        return successfulReturnBookMessage;
    }

    public String getInvalidReturnBookMessage() {
        return invalidReturnBookMessage;
    }


    public String getBookListMessage() {
        return bookListMessage;
    }

    public String getMovieListMessage() {
        return movieListMessage;
    }

    public String getSuccessfulCheckoutMovieMessage() {
        return successfulCheckoutMovieMessage;
    }

    public String getInvalidCheckoutMovieMessage() {
        return invalidCheckoutMovieMessage;
    }

    public String getSuccessfulReturnMovieMessage() {
        return successfulReturnMovieMessage;
    }

    public String getInvalidReturnMovieMessage() {
        return invalidReturnMovieMessage;
    }
}
