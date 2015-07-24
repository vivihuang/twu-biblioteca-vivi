package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.*;

import java.util.List;
import java.util.Scanner;

public class SystemReplyMessageService {

    private SystemReplyMessage systemReplyMessage = new SystemReplyMessage();
    private UserInputMessage userInputMessage = new UserInputMessage();
    private BookDetailsService bookDetailsService = new BookDetailsService();
    private ExchangeMessage outputExchangeMessage = new ExchangeMessage();

    public String waitForInputMessage(Scanner scanner) {
        String message = scanner.nextLine();
        return message;
    }

    public boolean getQuitMessage(String message) {
        if (message.equals(userInputMessage.getQuitMessage())) {
            return true;
        }
        return false;
    }

    public void printOutMessage(String message) {
        System.out.println(message);
    }

    public ExchangeMessage identifyMessage(String inputMessage,List<BookDetails> bookDetailsList) {

        if (inputMessage.contains(userInputMessage.getEnterSystemMessage())) {
            outputExchangeMessage = showWelcomeMessage();
        }
        else if (inputMessage.contains(userInputMessage.getMenuMessage())) {
            outputExchangeMessage = showMenuOptions();
        }
        else if (inputMessage.contains(userInputMessage.getBookListMessage())) {
            outputExchangeMessage = showBookList(bookDetailsList);
        }
        else if (inputMessage.contains(userInputMessage.getBookDetailMessage())) {
            outputExchangeMessage = showBookDetails(bookDetailsList);
        }
        else if (inputMessage.contains(userInputMessage.getCheckoutBookMessage())) {
            outputExchangeMessage = checkoutBook(inputMessage,bookDetailsList);
        }
        else if (inputMessage.contains(userInputMessage.getReturnBookMessage())) {
            outputExchangeMessage = returnBook(inputMessage, bookDetailsList);
        }
        else if (inputMessage.contains(userInputMessage.getQuitMessage())) {
            outputExchangeMessage = showQuitMessage();
        }
        else {
            outputExchangeMessage = invalidInput();
        }
        return outputExchangeMessage;
    }

    public ExchangeMessage invalidInput() {
        String returnMessage = "Invalid input! Please retry!";
        ExchangeMessage exchangeMessage = new ExchangeMessage(returnMessage);
        return exchangeMessage;
    }

    public ExchangeMessage showWelcomeMessage() {
//        if (inputMessage.equals(userInputMessage.getEnterSystemMessage())) {
            ExchangeMessage exchangeMessage = new ExchangeMessage(systemReplyMessage.getWelcomeMessage());
            return exchangeMessage;
//        }
//        return null;
    }

    public ExchangeMessage showQuitMessage() {
//        if (inputMessage.equals(userInputMessage.getQuitMessage())) {
            ExchangeMessage exchangeMessage = new ExchangeMessage(systemReplyMessage.getQuitMessage());
            return exchangeMessage;
//        }
//        return null;
    }

    public ExchangeMessage showMenuOptions() {
//        if (inputMessage.equals(userInputMessage.getMenuMessage())) {
            ExchangeMessage exchangeMessage = new ExchangeMessage(systemReplyMessage.getMenuOptions());
            return exchangeMessage;
//        }
//        return null;
    }

    public ExchangeMessage showBookList(List<BookDetails> bookDetailsList) {
//        if (inputMessage.equals(userInputMessage.getBookListMessage())) {
            String outputMessage = systemReplyMessage.getBookListMessage() + bookDetailsService.getBookList(bookDetailsList);
            ExchangeMessage exchangeMessage = new ExchangeMessage(outputMessage);
            return exchangeMessage;
//        }
//        return null;
    }

    public ExchangeMessage showBookDetails(List<BookDetails> bookDetailsList) {
//        String orderMessage = userInputMessage.getBookDetailMessage();
//        if (inputMessage.contains(orderMessage)) {
            ExchangeMessage exchangeMessage = new ExchangeMessage(bookDetailsService.getBookDetails(bookDetailsList));
            return exchangeMessage;
//        }
//        return null;
    }

    public ExchangeMessage checkoutBook(String inputMessage, List<BookDetails> bookDetailsList) {
        String orderMessage = userInputMessage.getCheckoutBookMessage();
        ExchangeMessage exchangeMessage = new ExchangeMessage();
//        if (inputMessage.contains(orderMessage)) {
            bookDetailsList = bookDetailsService.checkoutBook(inputMessage, orderMessage, bookDetailsList);
            if (bookDetailsList != null) {
                exchangeMessage.setOutputMessage(systemReplyMessage.getSuccessfulCheckoutMessage());
                exchangeMessage.setBookDetailsList(bookDetailsList);
            } else {
                exchangeMessage.setOutputMessage(systemReplyMessage.getInvalidCheckoutMessage());
            }
            return exchangeMessage;
//        }
//        return null;
    }

    public ExchangeMessage returnBook(String inputMessage, List<BookDetails> bookDetailsList) {
        ExchangeMessage exchangeMessage = new ExchangeMessage();
        String orderMessage = userInputMessage.getReturnBookMessage();
//        if (inputMessage.contains(orderMessage)) {
            bookDetailsList = bookDetailsService.returnBook(inputMessage, orderMessage, bookDetailsList);
            if (bookDetailsList != null) {
                exchangeMessage.setOutputMessage(systemReplyMessage.getSuccessfulReturnMessage());
                exchangeMessage.setBookDetailsList(bookDetailsList);
            } else {
                exchangeMessage.setOutputMessage(systemReplyMessage.getInvalidReturnMessage());
            }
            return exchangeMessage;
//        }
//        return null;
    }

}
