package com.lbms.lbms.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name ="books")
public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @Column(name = "isbn",nullable = false,unique = true)
        private String isbn;
        @Column(name = "title",nullable = false)
        private String title;
        @Column(name = "author",nullable = false)
        private String author;
        @Column(name = "isAvailable")
        private boolean isAvailable;

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getIsbn() {
                return isbn;
        }

        public void setIsbn(String isbn) {
                this.isbn = isbn;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public boolean isAvailable() {
                return isAvailable;
        }

        public void setAvailable(boolean available) {
                isAvailable = available;
        }



}
