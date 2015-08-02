package com.twu.biblioteca;

import com.twu.biblioteca.entity.*;
import com.twu.biblioteca.service.*;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private static BookService bookService = new BookService();
    private static MovieService movieService = new MovieService();
    private static UserService userService = new UserService();
    private static SystemReplyMessageService systemReplyMessageService = new SystemReplyMessageService();
    private static ExchangeMessage outputExchangeMessage = new ExchangeMessage();

    public static void main(String[] args) {
        List<Book> bookList = bookService.addBooksToList();
        List<Movie> movieList = movieService.addMoviesToList();
        List<UserAccount> userAccountList = userService.addUserAccount();
        boolean loginStatus = false;
        Scanner scanner = new Scanner(System.in);
        String inputMessage;
        String userNumber=null;

        do {
            inputMessage = systemReplyMessageService.waitForInputMessage(scanner);

            if (loginStatus) {
                outputExchangeMessage = systemReplyMessageService.identifyMessage(inputMessage, bookList, movieList, userNumber);
                if (outputExchangeMessage.getBookList() != null) {
                    bookList = outputExchangeMessage.getBookList();
                } else if (outputExchangeMessage.getMovieList() != null) {
                    movieList = outputExchangeMessage.getMovieList();
                }
            }
            else {
                outputExchangeMessage = systemReplyMessageService.loginService(inputMessage,userAccountList);
                loginStatus = outputExchangeMessage.isLoginStatus();
                userNumber = outputExchangeMessage.getUserNumber();
            }
            String outputMessage = outputExchangeMessage.getOutputMessage();
            systemReplyMessageService.printOutMessage(outputMessage);
        } while (!systemReplyMessageService.getQuitMessage(inputMessage));
    }
}
