package com.twu.biblioteca;

import com.twu.biblioteca.entity.*;
import com.twu.biblioteca.service.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    private UserInputMessage userInputMessage = new UserInputMessage();
    private SystemReplyMessageService systemReplyMessageService = new SystemReplyMessageService();
    private SystemReplyMessage systemReplyMessage = new SystemReplyMessage();
    private BookDetailsService bookDetailsService = new BookDetailsService();
    private List<BookDetails> allBookDetailsList = bookDetailsService.addBooksToList();
    private ExchangeMessage exchangeMessage = new ExchangeMessage();

    @Test
    public void showWelcomeMessageTest(){
        String expectedMessage = systemReplyMessage.getWelcomeMessage();
//        String inputMessage = userInputMessage.getEnterSystemMessage();
        exchangeMessage = systemReplyMessageService.showWelcomeMessage();
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void showQuitMessageTest() {
        String expectedMessage = systemReplyMessage.getQuitMessage();
//        String inputMessage = userInputMessage.getQuitMessage();
        exchangeMessage = systemReplyMessageService.showQuitMessage();
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void addBookToListTest(){
        String expectedBook = "And then there were none";
        String expectedAuthor = "Agatha Christie";
        int expectedPublishedYear = 2003;
        assertEquals(expectedBook,allBookDetailsList.get(2).getName());
        assertEquals(expectedAuthor,allBookDetailsList.get(2).getAuthor());
        assertEquals(expectedPublishedYear,allBookDetailsList.get(2).getYearPublished());
    }

    @Test
    public void addBookListTest() {
        String expectedMessage = "1. The Little Prince\n2. Flipped\n3. And then there were none\n";
        String actualMessage = bookDetailsService.getBookList(allBookDetailsList);
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void showMainMenuTest() {
        String expectedMessage = systemReplyMessage.getMenuOptions();
        exchangeMessage = systemReplyMessageService.showMenuOptions();
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void showBookListTest(){
        String expectedMessage = "The book list:\n1. The Little Prince\n2. Flipped\n3. And then there were none\n";
        exchangeMessage = systemReplyMessageService.showBookList(allBookDetailsList);
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void showBookDetailsTest() {
        String expectedMessage = "1. Book: The Little Prince; Author: Antoine de Saint-Exupéry; Published Year: 1942\n"+
                "2. Book: Flipped; Author: Van Draanen Wendelin; Published Year: 2003\n"+
                "3. Book: And then there were none; Author: Agatha Christie; Published Year: 2003\n";
//        String inputMessage = userInputMessage.getBookDetailMessage();
        exchangeMessage = systemReplyMessageService.showBookDetails(allBookDetailsList);
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void validCheckoutBookTest(){
        String expectedMessage = systemReplyMessage.getSuccessfulCheckoutMessage();
        String inputMessage = userInputMessage.getCheckoutBookMessage()+1;
        exchangeMessage = systemReplyMessageService.checkoutBook(inputMessage,allBookDetailsList);
        String actualMessage = exchangeMessage.getOutputMessage();
        List<BookDetails> actualBookDetailsList = exchangeMessage.getBookDetailsList();
        assertEquals(expectedMessage,actualMessage);
        assertEquals(2, actualBookDetailsList.size());
    }

    @Test
    public void invalidCheckoutBookTest() {
        String inputMessage = userInputMessage.getCheckoutBookMessage()+"124";
        String expectedMessage = systemReplyMessage.getInvalidCheckoutMessage();
        exchangeMessage = systemReplyMessageService.checkoutBook(inputMessage,allBookDetailsList);
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void validReturnBookTest(){
        String checkoutMessage = userInputMessage.getCheckoutBookMessage()+1;
        List<BookDetails> checkoutBookDetailsList = systemReplyMessageService.checkoutBook(checkoutMessage,allBookDetailsList).getBookDetailsList();
        String inputMessage = userInputMessage.getReturnBookMessage()+1;
        exchangeMessage = systemReplyMessageService.returnBook(inputMessage,checkoutBookDetailsList);
        List<BookDetails> returnBookDetailsList = exchangeMessage.getBookDetailsList();
        assertEquals(allBookDetailsList,returnBookDetailsList);
    }

    @Test
    public void invalidReturnBookTest(){
        String expectedMessage = systemReplyMessage.getInvalidReturnMessage();
        String inputMessage = userInputMessage.getReturnBookMessage()+"a";
        exchangeMessage = systemReplyMessageService.returnBook(inputMessage, allBookDetailsList);
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }
}
