package com.example.demo.book.controller;

import com.example.demo.aws.credentials.AwsCredentialsManager;
import com.example.demo.book.service.BookService;
import com.example.demo.models.BookModel;
import com.example.demo.util.ResponseModel;
import com.example.demo.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseModel createOrUpdateBook(@RequestBody BookModel bookModel) {
        ResponseModel rs = null;
        if (bookModel.getName() != null && bookModel.getAuthor() != null) {
            rs = bookService.createOrUpdateBook(bookModel);
        } else {
            rs = ResponseStatus.create("PARAM MISSING",
                    bookModel,
                    HttpStatus.ACCEPTED, HttpStatus.ACCEPTED.value());
        }
        return rs;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseModel createOrUpdateBook() {
        AwsCredentialsManager.getAppSecrets();
        return ResponseStatus.create("BOOK LISTED SUCCESSFULLY",
                bookService.findAll(),
                HttpStatus.OK, HttpStatus.OK.value());
    }


}
