package com.twu.biblioteca.entity;

public class UserInputMessage {
    private String enterSystemMessage = "hi";
    private String quitMessage = "quit";
    private String bookListMessage = "list";
    private String bookDetailMessage = "details";
    private String mainMenuMessage = "menu";
    private String checkoutBookMessage = "checkout";
    private String returnBookMessage = "return";

    public String getCheckoutBookMessage() {
        return checkoutBookMessage;
    }

    public String getReturnBookMessage() {
        return returnBookMessage;
    }

    public String getEnterSystemMessage() {
        return enterSystemMessage;
    }

    public String getQuitMessage() {
        return quitMessage;
    }

    public String getBookListMessage() {
        return bookListMessage;
    }

    public String getBookDetailMessage() {
        return bookDetailMessage;
    }

    public String getMenuMessage() {
        return mainMenuMessage;
    }
}
