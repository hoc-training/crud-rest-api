package com.hoc.training.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoc.training.entity.Book;

@RestController
public class BookController {

    private List<Book> books = new ArrayList<>();

    @GetMapping("/api/books")
    public List<Book> getAllBooks() {
        return books;
    }

    @PostMapping("/api/book")
    public String addBook(@RequestBody Book book) {
        books.add(book);
        return "Buku berhasil ditambahkan!";
    }
}
