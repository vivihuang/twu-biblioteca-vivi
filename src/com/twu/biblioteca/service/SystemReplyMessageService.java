package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SystemReplyMessageService {

    private SystemReplyMessage systemReplyMessage = new SystemReplyMessage();
    private UserInputMessage userInputMessage = new UserInputMessage();
    private BookDetailsService bookDetailsService = new BookDetailsService();

    public String waitForInputMessage(Scanner scanner) {
        String message = scanner.nextLine();
        return message;
    }

    public void printOutMessage(String message) {
        System.out.println(message);
    }


    public String showWelcomeMessage(String inputMessage) {
        if (inputMessage.equals(userInputMessage.getEnterSystemMessage())) {
            return systemReplyMessage.getWelcomeMessage();
        }
        return null;
    }

    public String showQuitMessage(String inputMessage) {
        if (inputMessage.equals(userInputMessage.getQuitMessage())) {
            return systemReplyMessage.getQuitMessage();
        }
        return null;
    }

    public String showMenuOptions(String inputMessage) {
        if (inputMessage.equals(userInputMessage.getMainMenuMessage())) {
            return systemReplyMessage.getMenuOptions();
        }
        return null;
    }

    public String showBookList(String inputMessage,List<BookDetails> bookDetailsList){
        if (inputMessage.equals(userInputMessage.getBookListMessage())) {
            return bookDetailsService.getBookList(bookDetailsList);
        }
        return null;
    }

    public String showBookDetails(String inputMessage,List<BookDetails> bookDetailsList) {
        String orderMessage = userInputMessage.getBookDetailMessage();
        if (inputMessage.contains(orderMessage)) {
            return bookDetailsService.getBookDetails(bookDetailsList);
        }
        return null;
    }

    public List<Object> checkoutBook (String inputMessage,List<BookDetails> bookDetailsList) {
        List<Object> objectList = new ArrayList<Object>();
        String orderMessage = userInputMessage.getCheckoutBookMessage();
        if (inputMessage.contains(orderMessage)) {
            bookDetailsList = bookDetailsService.checkoutBook(inputMessage,orderMessage,bookDetailsList);
            if (bookDetailsList != null) {
                objectList.add(systemReplyMessage.getSuccessfulCheckoutMessage());
                objectList.add(bookDetailsList);
            }
            else {
                objectList.add(systemReplyMessage.getInvalidCheckoutMessage());
            }
            return objectList;
        }
        return null;
    }

    public List<Object> returnBook(String inputMessage,List<BookDetails> bookDetailsList) {
        List<Object> objectList = new ArrayList<Object>();
        String orderMessage = userInputMessage.getReturnBookMessage();
        if (inputMessage.contains(orderMessage)) {
            bookDetailsList = bookDetailsService.returnBook(inputMessage,orderMessage,bookDetailsList);
            if (bookDetailsList!=null) {
                objectList.add(systemReplyMessage.getQuitMessage());
                objectList.add(bookDetailsList);
            }
            else {
                objectList.add(systemReplyMessage.getInvalidReturnMessage());
            }
            return objectList;
        }
        return null;
    }

}
