package com.twu.biblioteca;

import com.twu.biblioteca.entity.*;
import com.twu.biblioteca.service.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    private BibliotecaApp bibliotecaApp = new BibliotecaApp();
    private UserInputMessage userInputMessage = new UserInputMessage();
    private SystemReplyMessageService systemReplyMessageService = new SystemReplyMessageService();
    private SystemReplyMessage systemReplyMessage = new SystemReplyMessage();
    private BookDetailsService bookDetailsService = new BookDetailsService();
    private List<BookDetails> allBookDetailsList = bookDetailsService.addBooksToList();

    @Test
    public void showWelcomeMessageTest(){
        String expectedMessage = systemReplyMessage.getWelcomeMessage();
        String inputMessage = userInputMessage.getEnterSystemMessage();
        String actualMessage = systemReplyMessageService.showWelcomeMessage(inputMessage);
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void showQuitMessageTest() {
        String expectedMessage = systemReplyMessage.getQuitMessage();
        String inputMessage = userInputMessage.getQuitMessage();
        String actualMessage = systemReplyMessageService.showQuitMessage(inputMessage);
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
        String actualMessage = systemReplyMessageService.showMenuOptions(userInputMessage.getMainMenuMessage());
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void showBookListTest(){
        String expectedMessage = "1. The Little Prince\n2. Flipped\n3. And then there were none\n";
        String actualMessage = systemReplyMessageService.showBookList(userInputMessage.getBookListMessage(),allBookDetailsList);
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void showBookDetailsTest() {
        String expectedMessage = "1. Book: The Little Prince; Author: Antoine de Saint-Exupéry; Published Year: 1942\n"+
                "2. Book: Flipped; Author: Van Draanen Wendelin; Published Year: 2003\n"+
                "3. Book: And then there were none; Author: Agatha Christie; Published Year: 2003\n";
        String inputMessage = userInputMessage.getBookDetailMessage();
        String actualMessage = systemReplyMessageService.showBookDetails(inputMessage, allBookDetailsList);
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void validCheckoutBookTest(){
        String expectedMessage = systemReplyMessage.getSuccessfulCheckoutMessage();
        String inputMessage = userInputMessage.getCheckoutBookMessage()+1;
        List<Object> objectList = systemReplyMessageService.checkoutBook(inputMessage, allBookDetailsList);
        String actualMessage = (String)objectList.get(0);
        List<BookDetails> actualBookDetailsList = (List<BookDetails>)objectList.get(1);
        assertEquals(expectedMessage,actualMessage);
        assertEquals(2, actualBookDetailsList.size());
    }

    @Test
    public void invalidCheckoutBookTest() {
        String inputMessage = userInputMessage.getCheckoutBookMessage()+"124";
        String expectedMessage = systemReplyMessage.getInvalidCheckoutMessage();
        List<Object> objectList = systemReplyMessageService.checkoutBook(inputMessage, allBookDetailsList);
        String actualMessage = (String)objectList.get(0);
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void validReturnBookTest(){
        String checkoutMessage = userInputMessage.getCheckoutBookMessage()+1;
        List<Object> checkoutObjectList = systemReplyMessageService.checkoutBook(checkoutMessage,allBookDetailsList);
        List<BookDetails> checkoutBookDetailsList = (List<BookDetails>)checkoutObjectList.get(1);
        String returnMessage = userInputMessage.getReturnBookMessage()+1;
        List<Object> returnObjectList = systemReplyMessageService.returnBook(returnMessage, checkoutBookDetailsList);
        List<BookDetails> returnBookDetailsList = (List<BookDetails>)returnObjectList.get(1);
        assertEquals(allBookDetailsList,returnBookDetailsList);
    }

    @Test
    public void invalidReturnBookTest(){
        String expectedMessage = systemReplyMessage.getInvalidReturnMessage();
        String returnMessage = userInputMessage.getReturnBookMessage()+"a";
        List<Object> checkoutObjectList = systemReplyMessageService.returnBook(returnMessage,allBookDetailsList);
        String actualMessage = (String)checkoutObjectList.get(0);
        assertEquals(expectedMessage,actualMessage);
    }
}
