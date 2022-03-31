package com.example.demo.book.service;

import com.example.demo.entities.Book;
import com.example.demo.models.BookModel;
import com.example.demo.util.ResponseModel;

import java.util.List;

public interface BookService {
    
    ResponseModel createOrUpdateBook(BookModel bookModel);

    List<Book> findAll();
}
