package com.lbms.lbms.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name ="users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @Column(name = "name",nullable = false)
        private String name;
        @Column(name = "email",unique = true)
        private String email;
        @Column(name = "borrowedBooks")
        private String borrowedBooks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(String borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
