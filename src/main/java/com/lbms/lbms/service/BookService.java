package com.lbms.lbms.service;

import com.lbms.lbms.entity.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);
    List<Book> listAllAvailableBooks();
    List<Book> listAllBorrowedBooks(String email);

    String borrowBook(String isbn, String email);
    String returnBook(String isbn, String userEmail);
}
