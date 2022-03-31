package com.example.demo.book.service;

import com.example.demo.book.dao.BookDao;
import com.example.demo.entities.Book;
import com.example.demo.models.BookModel;
import com.example.demo.util.ResponseModel;
import com.example.demo.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public ResponseModel createOrUpdateBook(BookModel bookModel) {
        Book book = null;
        if (bookModel.getId() != null) {
            book = bookDao.findById(bookModel.getId()).orElse(null);
            if (book == null) {
                return ResponseStatus.create("Book Not Found",
                        bookDao.save(book),
                        HttpStatus.OK, HttpStatus.OK.value());
            }
        } else {
            book = new Book();
        }

        book.setName(bookModel.getName());
        book.setAuthor(bookModel.getAuthor());

        return ResponseStatus.create("Book Saved Successfully",
                bookDao.save(book),
                HttpStatus.OK, HttpStatus.OK.value());
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }
}
