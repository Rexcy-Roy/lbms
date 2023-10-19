package com.lbms.lbms.controller;

import com.lbms.lbms.entity.Book;
import com.lbms.lbms.entity.User;
import com.lbms.lbms.service.BookService;
import com.lbms.lbms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    private BookService bookService;
    private UserService userService;


   @Autowired
    public LibraryController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService= userService;
    }

    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
       return userService.registerUser(user);
    }
    @GetMapping("/availableBooks")
    public List<Book> listAvailableBooks() {
        return bookService.listAllAvailableBooks();
    }

    @GetMapping("/borrowedBooks")
    public List<Book> listAllBorrowedBooks(@RequestParam String email) {
        return bookService.listAllBorrowedBooks(email);
    }

    @PostMapping("/borrowBook")
    public String borrowBook(@RequestParam String isbn, @RequestParam String email) {

        return  bookService.borrowBook(isbn, email);

    }
    @PostMapping("/returnBook")
    public String returnBook(@RequestParam String isbn, @RequestParam String email) {

        return  bookService.returnBook(isbn, email);

    }

}
