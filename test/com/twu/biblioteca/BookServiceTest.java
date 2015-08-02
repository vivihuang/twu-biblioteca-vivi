package com.twu.biblioteca;

import com.twu.biblioteca.entity.*;
import com.twu.biblioteca.service.*;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class BookServiceTest {

    private BookService bookService = new BookService();
    private List<Book> allBookList = bookService.addBooksToList();
    private UserInputMessage userInputMessage = new UserInputMessage();
    private SystemReplyMessage systemReplyMessage = new SystemReplyMessage();
    private SystemReplyMessageService systemReplyMessageService = new SystemReplyMessageService();
    private ExchangeMessage exchangeMessage = new ExchangeMessage();

    @Test
    public void addBookToListTest(){
        String expectedBook = "And then there were none";
        String expectedAuthor = "Agatha Christie";
        int expectedPublishedYear = 2003;
        assertEquals(expectedBook, allBookList.get(2).getName());
        assertEquals(expectedAuthor, allBookList.get(2).getAuthor());
        assertEquals(expectedPublishedYear, allBookList.get(2).getYearPublished());
    }

    @Test
    public void addBookListTest() {
        String expectedMessage = "1. The Little Prince\n2. Flipped\n3. And then there were none\n";
        String actualMessage = bookService.getBookList(allBookList);
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void showBookListTest(){
        String expectedMessage = "The book list:\n1. The Little Prince\n2. Flipped\n3. And then there were none\n";
        exchangeMessage = systemReplyMessageService.showBookList(allBookList);
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void showBookDetailsTest() {
        String expectedMessage = "1. Book: The Little Prince; Author: Antoine de Saint-Exupéry; Published Year: 1942\n"+
                "2. Book: Flipped; Author: Van Draanen Wendelin; Published Year: 2003\n"+
                "3. Book: And then there were none; Author: Agatha Christie; Published Year: 2003\n";
        exchangeMessage = systemReplyMessageService.showBookDetails(allBookList);
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void validCheckoutBookTest(){
        String expectedMessage = systemReplyMessage.getSuccessfulCheckoutBookMessage();
        String inputMessage = userInputMessage.getCheckoutBookMessage()+1;
        exchangeMessage = systemReplyMessageService.checkoutBook(inputMessage, allBookList);
        String actualMessage = exchangeMessage.getOutputMessage();
        List<Book> actualBookList = exchangeMessage.getBookList();
        assertEquals(expectedMessage,actualMessage);
        assertEquals(2, actualBookList.size());
    }

    @Test
    public void invalidCheckoutBookTest() {
        String inputMessage = userInputMessage.getCheckoutBookMessage()+"124";
        String expectedMessage = systemReplyMessage.getInvalidCheckoutBookMessage();
        exchangeMessage = systemReplyMessageService.checkoutBook(inputMessage, allBookList);
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void validReturnBookTest(){
        String checkoutMessage = userInputMessage.getCheckoutBookMessage()+1;
        List<Book> checkoutBookList = systemReplyMessageService.checkoutBook(checkoutMessage, allBookList).getBookList();
        String inputMessage = userInputMessage.getReturnBookMessage()+1;
        exchangeMessage = systemReplyMessageService.returnBook(inputMessage, checkoutBookList);
        List<Book> returnBookList = exchangeMessage.getBookList();
        assertEquals(allBookList, returnBookList);
    }

    @Test
    public void invalidReturnBookTest(){
        String expectedMessage = systemReplyMessage.getInvalidReturnBookMessage();
        String inputMessage = userInputMessage.getReturnBookMessage()+"a";
        exchangeMessage = systemReplyMessageService.returnBook(inputMessage, allBookList);
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }

}
