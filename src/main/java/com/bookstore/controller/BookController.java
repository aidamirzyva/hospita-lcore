package com.bookstore.controller;

import com.bookstore.dto.request.ReqBook;
import com.bookstore.dto.request.ReqStudent;
import com.bookstore.dto.request.ReqToken;
import com.bookstore.dto.response.RespBook;
import com.bookstore.dto.response.RespStudent;
import com.bookstore.dto.response.Response;
import com.bookstore.service.BookService;
import com.bookstore.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    @PostMapping(value = "/GetBookList")
    public Response<List<RespBook>> getStudentList(@RequestBody ReqToken reqToken){


        return bookService.getBookList(reqToken);

    }
    @PostMapping("/GetBookById")
    public Response<RespBook> getBookById(@RequestBody ReqBook reqBook){
        return bookService.getBookById(reqBook);
    }

    @PostMapping("/AddBook")
    public Response addBook(@RequestBody ReqBook reqBook) {
        return bookService.addBook(reqBook);
    }
    @PostMapping("/UpdateStudent")
    public Response updateBook(@RequestBody ReqBook reqBook){
        return bookService.updateBook(reqBook);
    }
    @PostMapping("/DeleteBook")
    public Response deleteStudent(@RequestBody ReqBook reqBook){
        return bookService.deleteBook(reqBook);
    }

}
