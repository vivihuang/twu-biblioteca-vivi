package com.twu.biblioteca.entity;

import java.util.List;

/**
 * Created by Vivi on 7/24/15.
 */
public class ExchangeMessage {

    private String outputMessage;
    private List<BookDetails> bookDetailsList;

    public ExchangeMessage() {
    }

    public ExchangeMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    public ExchangeMessage(String outputMessage, List<BookDetails> bookDetailsList) {
        this.outputMessage = outputMessage;
        this.bookDetailsList = bookDetailsList;
    }

    public String getOutputMessage() {
        return outputMessage;
    }

    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    public void setBookDetailsList(List<BookDetails> bookDetailsList) {
        this.bookDetailsList = bookDetailsList;
    }

    public List<BookDetails> getBookDetailsList() {
        return bookDetailsList;
    }
}
