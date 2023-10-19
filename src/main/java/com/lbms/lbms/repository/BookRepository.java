package com.lbms.lbms.repository;

import com.lbms.lbms.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);

    List<Book> findByIsAvailable(boolean b);

    List<Book> findByIsbnIn(List<String> borrowedBookISBNs);
}
