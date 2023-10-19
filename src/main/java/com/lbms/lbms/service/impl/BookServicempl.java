package com.lbms.lbms.service.impl;

import com.lbms.lbms.entity.Book;
import com.lbms.lbms.entity.User;
import com.lbms.lbms.exception.BookAlreadyExistsException;
import com.lbms.lbms.exception.BookNotBorrowedException;
import com.lbms.lbms.exception.BookNotFoundException;
import com.lbms.lbms.exception.UserNotFoundException;
import com.lbms.lbms.repository.BookRepository;
import com.lbms.lbms.repository.UserRepository;
import com.lbms.lbms.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BookServicempl implements BookService {
    private final BookRepository bookRepository;
    private UserRepository userRepository;


    public BookServicempl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {

        Book existingBook = bookRepository.findByIsbn(book.getIsbn())
                .orElseThrow(() ->new BookAlreadyExistsException("Book with the same ISBN already exists"));

        book.setAvailable(true);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> listAllAvailableBooks() {
        return bookRepository.findByIsAvailable(true);
    }

    @Override
    public List<Book> listAllBorrowedBooks(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));

        String borrowedBooks = user.getBorrowedBooks();

        if (borrowedBooks.isEmpty()) {
            throw new BookNotBorrowedException("User with email " + email + " has not borrowed any books.");
        }

        List<String> borrowedBookISBNs = Arrays.asList(borrowedBooks.split(","));
        return bookRepository.findByIsbnIn(borrowedBookISBNs);
    }


    @Override
    public String borrowBook(String isbn, String email) {

        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));


        if (!book.isAvailable()) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " is not available.");
        }


        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));


        user.setBorrowedBooks(user.getBorrowedBooks() + "," + book.getTitle());
        book.setAvailable(false);
        userRepository.save(user);
        bookRepository.save(book);

        return "Book with ISBN " + isbn + " has been successfully borrowed by " + user.getName();
    }

    @Override
    public String returnBook(String isbn, String userEmail) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));


        if (book.isAvailable()) {
            throw new BookNotBorrowedException("Book with ISBN " + isbn + " is not borrowed by the user.");
        }


        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User with email " + userEmail + " not found"));


        if (user.getBorrowedBooks() == null ) {
            throw new BookNotBorrowedException("User with email " + userEmail + " has not borrowed the book with ISBN " + isbn);
        }


        book.setAvailable(true);
        bookRepository.save(book);


        String borrowedBooks = user.getBorrowedBooks().replace(isbn, "");
        user.setBorrowedBooks(borrowedBooks);
        userRepository.save(user);

        return "Book with ISBN " + isbn + " has been successfully returned by " + user.getName();

    }


}
