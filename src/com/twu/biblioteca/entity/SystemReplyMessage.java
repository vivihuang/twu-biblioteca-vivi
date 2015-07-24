package com.twu.biblioteca.entity;

public class SystemReplyMessage {

    private String welcomeMessage = "Welcome to the Bangalore public library management system!";
    private String invalidMenuMessage = "Select a valid option!";
    private String quitMessage = "Quit the system successful!";
    private String menuOptions = "List Books.";
    private String successfulCheckoutMessage = "Thank you! Enjoy the book!";
    private String invalidCheckoutMessage = "That book is not available.";
    private String successfulReturnMessage = "Thank you for returning the book.";
    private String invalidReturnMessage = "That is not a valid book to return.";

    public String getWelcomeMessage() {
        return welcomeMessage;
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

    public String getSuccessfulCheckoutMessage() {
        return successfulCheckoutMessage;
    }

    public String getInvalidCheckoutMessage() {
        return invalidCheckoutMessage;
    }

    public String getSuccessfulReturnMessage() {
        return successfulReturnMessage;
    }

    public String getInvalidReturnMessage() {
        return invalidReturnMessage;
    }
}
