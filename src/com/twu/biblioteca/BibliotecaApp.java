package com.twu.biblioteca;

import com.twu.biblioteca.entity.*;
import com.twu.biblioteca.service.*;

import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private static BookDetailsService bookDetailsService = new BookDetailsService();
    private static SystemReplyMessageService systemReplyMessageService = new SystemReplyMessageService();
    private static ExchangeMessage outputEchangeMessage = new ExchangeMessage();

    public static void main(String[] args) {
        List<BookDetails> bookDetailsList = bookDetailsService.addBooksToList();
        Scanner scanner = new Scanner(System.in);
        String inputMessage;

        do {
            inputMessage = systemReplyMessageService.waitForInputMessage(scanner);
            outputEchangeMessage = systemReplyMessageService.identifyMessage(inputMessage,bookDetailsList);
            if (outputEchangeMessage.getBookDetailsList() !=null) {
                bookDetailsList = outputEchangeMessage.getBookDetailsList();
            }
            String outputMessage = outputEchangeMessage.getOutputMessage();
            systemReplyMessageService.printOutMessage(outputMessage);
        } while (!systemReplyMessageService.getQuitMessage(inputMessage));
    }
}
