package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.BookDetails;
import com.twu.biblioteca.entity.UserInputMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vivi on 7/23/15.
 */
public class BookDetailsService {
    private BookDetails bookDetails;

    public List<BookDetails> addBooksToList(){
        List<BookDetails> bookDetailsList = new ArrayList<BookDetails>();
        BookDetails bookDetails1 = new BookDetails(1,"The Little Prince","Antoine de Saint-Exupéry",1942);
        bookDetailsList.add(bookDetails1);
        BookDetails bookDetails2 = new BookDetails(2,"Flipped","Van Draanen Wendelin",2003);
        bookDetailsList.add(bookDetails2);
        BookDetails bookDetails3 = new BookDetails(3,"And then there were none","Agatha Christie",2003);
        bookDetailsList.add(bookDetails3);
        return bookDetailsList;
    }

    public String getBookList(List<BookDetails> bookDetailsList) {
        String bookList = "";
        for (BookDetails bookDetails:bookDetailsList) {
            bookList = bookList+bookDetails.getId()+". "+bookDetails.getName()+"\n";
        }
        return bookList;
    }

    public String getBookDetails(List<BookDetails> bookDetailsList) {
        String bookDetailMessage="";
        for (BookDetails bookDetails:bookDetailsList) {
            bookDetailMessage = bookDetailMessage+bookDetails.getId()+". Book: " + bookDetails.getName() + "; " +
                    "Author: " + bookDetails.getAuthor() + "; " +
                    "Published Year: " + bookDetails.getYearPublished() + "\n";
        }
        return bookDetailMessage;
    }

    public String getBookIndex(String inputMessage,String orderMessage,List<BookDetails> bookDetailsList){
        int orderLength = orderMessage.length();
        String bookIndex = inputMessage.substring(orderLength);
        if (checkBookIndexValid(bookIndex)) {
            for (BookDetails bookDetails:bookDetailsList) {
                if (bookDetails.getId() == Integer.parseInt(bookIndex)) {
                    return bookIndex;
                }
            }
        }
        return null;
    }

    public boolean checkBookIndexValid (String inputBookIndex) {
        char[] charArray = inputBookIndex.toCharArray();
        for (int i=0;i<charArray.length;i++) {
            byte ascii = (byte) charArray[i];
            if (ascii < 48 || ascii > 57) {
                return false;
            }
        }
        return true;
    }

    public List<BookDetails> checkoutBook(String inputMessage,String orderMessage,List<BookDetails> bookDetailsList) {
        String bookIndex = getBookIndex(inputMessage, orderMessage, bookDetailsList);
        if (bookIndex != null) {
            for (BookDetails bookDetails:bookDetailsList) {
                if (bookDetails.getId() == Integer.parseInt(bookIndex)) {
                    bookDetailsList.remove(bookDetails);
                    return bookDetailsList;
                }
            }
        }
        return null;
    }

    public List<BookDetails> returnBook(String inputMessage,String orderMessage,List<BookDetails> bookDetailsList) {
        List<BookDetails> allBookDetailsList = addBooksToList();
        String bookIndex = getBookIndex(inputMessage, orderMessage, allBookDetailsList);
        if (bookIndex != null && !checkReturnBookOnShelf(bookIndex,bookDetailsList)) {
            bookDetails = getBookDetailsById(bookIndex,allBookDetailsList);
            bookDetailsList.add(bookDetails);
            return bookDetailsList;
        }
        return null;
    }

    public boolean checkReturnBookOnShelf(String bookIndex,List<BookDetails> bookDetailsList){
        for (BookDetails bookDetails:bookDetailsList) {
            if (bookDetails.getId()==Integer.parseInt(bookIndex)) {
                return true;
            }
        }
        return false;
    }

    public BookDetails getBookDetailsById(String bookIndex,List<BookDetails> allBookDetailsList) {
        for (BookDetails bookDetails:allBookDetailsList) {
            if (bookDetails.getId() == Integer.parseInt(bookIndex)) {
                return bookDetails;
            }
        }
        return null;
    }
}
