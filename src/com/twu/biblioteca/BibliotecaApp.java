package com.twu.biblioteca;

import com.twu.biblioteca.entity.*;
import com.twu.biblioteca.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private static SystemReplyMessage systemReplyMessage = new SystemReplyMessage();
    private static UserInputMessage userInputMessage = new UserInputMessage();
    private static BookDetailsService bookDetailsService = new BookDetailsService();
    private static List<BookDetails> bookDetailsList = new ArrayList<BookDetails>();

    public static void main(String[] args) {
        bookDetailsList = bookDetailsService.addBooksToList();
        Scanner scanner = new Scanner(System.in);
        String message;


//        do {
//            message = waitForInputMessage(scanner);
//
//        } while ();
    }
}
