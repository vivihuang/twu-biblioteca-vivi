package com.twu.biblioteca;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.twu.biblioteca.entity.*;
import com.twu.biblioteca.service.*;

import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private static BookService bookService = new BookService();
    private static MovieService movieService = new MovieService();
    private static SystemReplyMessageService systemReplyMessageService = new SystemReplyMessageService();
    private static ExchangeMessage outputExchangeMessage = new ExchangeMessage();

    public static void main(String[] args) {
        List<Book> bookList = bookService.addBooksToList();
        List<Movie> movieList = movieService.addMoviesToList();
        Boolean loginStatus = false;
        Scanner scanner = new Scanner(System.in);
        String inputMessage;

        do {
            inputMessage = systemReplyMessageService.waitForInputMessage(scanner);

            if (loginStatus) {
                outputExchangeMessage = systemReplyMessageService.identifyMessage(inputMessage, bookList, movieList);
                if (outputExchangeMessage.getBookList() != null) {
                    bookList = outputExchangeMessage.getBookList();
                } else if (outputExchangeMessage.getMovieList() != null) {
                    movieList = outputExchangeMessage.getMovieList();
                }
            }
//            else {
//                systemReplyMessageService.login()
//            }
            String outputMessage = outputExchangeMessage.getOutputMessage();
            systemReplyMessageService.printOutMessage(outputMessage);
        } while (!systemReplyMessageService.getQuitMessage(inputMessage));
    }
}
