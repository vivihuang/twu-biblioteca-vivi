package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.*;

import java.util.List;
import java.util.Scanner;

public class SystemReplyMessageService {

    private SystemReplyMessage systemReplyMessage = new SystemReplyMessage();
    private UserInputMessage userInputMessage = new UserInputMessage();
    private BookService bookService = new BookService();
    private ExchangeMessage outputExchangeMessage = new ExchangeMessage();
    private MovieService movieService = new MovieService();
    private UserService userService = new UserService();

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

    public ExchangeMessage loginService(String inputMessage,List<UserAccount> userAccountList) {
        if (inputMessage.contains(userInputMessage.getLoginMessage())) {
            outputExchangeMessage.setOutputMessage(systemReplyMessage.getLoginAccountMessage());
        }
        else {
            String userNumber = userService.checkLoginStatus(inputMessage, userAccountList);
            if (userNumber!=null) {
                outputExchangeMessage.setUserNumber(userNumber);
                outputExchangeMessage.setLoginStatus(true);
                outputExchangeMessage.setOutputMessage(systemReplyMessage.getWelcomeMessage());
            }
            else {
                outputExchangeMessage.setOutputMessage(systemReplyMessage.getLoginMessage());
            }
        }
        return outputExchangeMessage;
    }

    public ExchangeMessage identifyMessage(String inputMessage,List<Book> bookList,List<Movie> movieList, String userNumber) {
        if (inputMessage.contains(userInputMessage.getEnterSystemMessage())) {
            outputExchangeMessage = showWelcomeMessage();
        }
        else if (inputMessage.contains(userInputMessage.getUserInfoMessage())) {
            outputExchangeMessage = showUserInfoMessage(userNumber);
        }
        else if (inputMessage.contains(userInputMessage.getMenuMessage())) {
            outputExchangeMessage = showMenuOptions();
        }
        else if (inputMessage.contains(userInputMessage.getBookListMessage())) {
            outputExchangeMessage = showBookList(bookList);
        }
        else if (inputMessage.contains(userInputMessage.getBookDetailMessage())) {
            outputExchangeMessage = showBookDetails(bookList);
        }
        else if (inputMessage.contains(userInputMessage.getCheckoutBookMessage())) {
            outputExchangeMessage = checkoutBook(inputMessage, bookList);
        }
        else if (inputMessage.contains(userInputMessage.getReturnBookMessage())) {
            outputExchangeMessage = returnBook(inputMessage, bookList);
        }
        else if (inputMessage.contains(userInputMessage.getMovieListMessage())) {
            outputExchangeMessage = showMovieList(movieList);
        }
        else if (inputMessage.contains(userInputMessage.getMovieDetailMessage())) {
            outputExchangeMessage = showMovieDetails(movieList);
        }
        else if (inputMessage.contains(userInputMessage.getCheckoutMovieMessage())) {
            outputExchangeMessage = checkoutMovie(inputMessage,movieList);
        }
        else if (inputMessage.contains(userInputMessage.getReturnMovieMessage())) {
            outputExchangeMessage = returnMovie(inputMessage,movieList);
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

    public ExchangeMessage showUserInfoMessage(String userNumber){
        ExchangeMessage exchangeMessage = new ExchangeMessage(userService.showUserInfo(userNumber));
        return exchangeMessage;
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

    public ExchangeMessage showBookList(List<Book> bookList) {
//        if (inputMessage.equals(userInputMessage.getBookListMessage())) {
            String outputMessage = systemReplyMessage.getBookListMessage() + bookService.getBookList(bookList);
            ExchangeMessage exchangeMessage = new ExchangeMessage(outputMessage);
            return exchangeMessage;
//        }
//        return null;
    }

    public ExchangeMessage showBookDetails(List<Book> bookList) {
//        String orderMessage = userInputMessage.getBookDetailMessage();
//        if (inputMessage.contains(orderMessage)) {
            ExchangeMessage exchangeMessage = new ExchangeMessage(bookService.getBookDetails(bookList));
            return exchangeMessage;
//        }
//        return null;
    }

    public ExchangeMessage checkoutBook(String inputMessage, List<Book> bookList) {
        String orderMessage = userInputMessage.getCheckoutBookMessage();
        ExchangeMessage exchangeMessage = new ExchangeMessage();
//        if (inputMessage.contains(orderMessage)) {
            bookList = bookService.checkoutBook(inputMessage, orderMessage, bookList);
            if (bookList != null) {
                exchangeMessage.setOutputMessage(systemReplyMessage.getSuccessfulCheckoutBookMessage());
                exchangeMessage.setBookList(bookList);
            } else {
                exchangeMessage.setOutputMessage(systemReplyMessage.getInvalidCheckoutBookMessage());
            }
            return exchangeMessage;
//        }
//        return null;
    }

    public ExchangeMessage returnBook(String inputMessage, List<Book> bookList) {
        ExchangeMessage exchangeMessage = new ExchangeMessage();
        String orderMessage = userInputMessage.getReturnBookMessage();
//        if (inputMessage.contains(orderMessage)) {
            bookList = bookService.returnBook(inputMessage, orderMessage, bookList);
            if (bookList != null) {
                exchangeMessage.setOutputMessage(systemReplyMessage.getSuccessfulReturnBookMessage());
                exchangeMessage.setBookList(bookList);
            } else {
                exchangeMessage.setOutputMessage(systemReplyMessage.getInvalidReturnBookMessage());
            }
            return exchangeMessage;
//        }
//        return null;
    }

    public ExchangeMessage showMovieList(List<Movie> movieList) {
        String outputMessage = systemReplyMessage.getMovieListMessage() + movieService.getMovieList(movieList);
        ExchangeMessage exchangeMessage = new ExchangeMessage(outputMessage);
        return exchangeMessage;
    }

    public ExchangeMessage showMovieDetails(List<Movie> movieList) {
        ExchangeMessage exchangeMessage = new ExchangeMessage(movieService.getMovieDetails(movieList));
        return exchangeMessage;
    }

    public ExchangeMessage checkoutMovie(String inputMessage,List<Movie> movieList) {
        ExchangeMessage exchangeMessage = new ExchangeMessage();
        String orderMessage = userInputMessage.getCheckoutMovieMessage();
        movieList = movieService.checkoutMovie(inputMessage,orderMessage,movieList);
        if (movieList != null) {
            exchangeMessage.setOutputMessage(systemReplyMessage.getSuccessfulCheckoutMovieMessage());
            exchangeMessage.setMovieList(movieList);
        } else {
            exchangeMessage.setOutputMessage(systemReplyMessage.getInvalidCheckoutMovieMessage());
        }
        return exchangeMessage;
    }

    public ExchangeMessage returnMovie(String inputMessage, List<Movie> movieList) {
        ExchangeMessage exchangeMessage = new ExchangeMessage();
        String orderMessage = userInputMessage.getReturnMovieMessage();
        movieList = movieService.returnMovie(inputMessage, orderMessage, movieList);
        if (movieList != null) {
            exchangeMessage.setOutputMessage(systemReplyMessage.getSuccessfulReturnMovieMessage());
            exchangeMessage.setMovieList(movieList);
        } else {
            exchangeMessage.setOutputMessage(systemReplyMessage.getInvalidReturnMovieMessage());
        }
        return exchangeMessage;
    }

}
