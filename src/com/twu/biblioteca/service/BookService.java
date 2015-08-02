package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private Book book;

    public List<Book> addBooksToList(){
        List<Book> bookList = new ArrayList<Book>();
        Book book1 = new Book(1,"The Little Prince","Antoine de Saint-Exupéry",1942);
        bookList.add(book1);
        Book book2 = new Book(2,"Flipped","Van Draanen Wendelin",2003);
        bookList.add(book2);
        Book book3 = new Book(3,"And then there were none","Agatha Christie",2003);
        bookList.add(book3);
        return bookList;
    }

    public String getBookList(List<Book> bookDetailsList) {
        String bookList = "";
        for (Book book :bookDetailsList) {
            bookList = bookList+ book.getId()+". "+ book.getName()+"\n";
        }
        return bookList;
    }

    public String getBookDetails(List<Book> bookList) {
        String bookDetailMessage="";
        for (Book book : bookList) {
            bookDetailMessage = bookDetailMessage+ book.getId()+". Book: " + book.getName() + "; " +
                    "Author: " + book.getAuthor() + "; " +
                    "Published Year: " + book.getYearPublished() + "\n";
        }
        return bookDetailMessage;
    }

    public String getBookIndex(String inputMessage,String orderMessage,List<Book> bookList){
        int orderLength = orderMessage.length();
        String bookIndex = inputMessage.substring(orderLength);
        if (checkBookIndexValid(bookIndex)) {
            for (Book book : bookList) {
                if (book.getId() == Integer.parseInt(bookIndex)) {
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

    public List<Book> checkoutBook(String inputMessage,String orderMessage,List<Book> bookList) {
        String bookIndex = getBookIndex(inputMessage, orderMessage, bookList);
        if (bookIndex != null) {
            for (Book book : bookList) {
                if (book.getId() == Integer.parseInt(bookIndex)) {
                    bookList.remove(book);
                    return bookList;
                }
            }
        }
        return null;
    }

    public List<Book> returnBook(String inputMessage,String orderMessage,List<Book> bookList) {
        List<Book> allBookList = addBooksToList();
        String bookIndex = getBookIndex(inputMessage, orderMessage, allBookList);
        if (bookIndex != null && !checkReturnBookOnShelf(bookIndex, bookList)) {
            book = getBookDetailsById(bookIndex, allBookList);
            bookList.add(book);
            return bookList;
        }
        return null;
    }

    public boolean checkReturnBookOnShelf(String bookIndex,List<Book> bookList){
        for (Book book : bookList) {
            if (book.getId()==Integer.parseInt(bookIndex)) {
                return true;
            }
        }
        return false;
    }

    public Book getBookDetailsById(String bookIndex,List<Book> allBookList) {
        for (Book book : allBookList) {
            if (book.getId() == Integer.parseInt(bookIndex)) {
                return book;
            }
        }
        return null;
    }
}
