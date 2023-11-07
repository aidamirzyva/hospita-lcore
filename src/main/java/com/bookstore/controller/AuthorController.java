package com.bookstore.controller;

import com.bookstore.dto.request.ReqAuthor;
import com.bookstore.dto.request.ReqBook;
import com.bookstore.dto.request.ReqToken;
import com.bookstore.dto.response.RespAuthor;
import com.bookstore.dto.response.RespBook;
import com.bookstore.dto.response.Response;
import com.bookstore.service.AuthorService;
import com.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    @PostMapping(value = "/GetAuthorList")
    public Response<List<RespBook>> getStudentList(@RequestBody ReqToken reqToken){


        return authorService.getAuthorList(reqToken);

    }
    @PostMapping("/GetAuthorById")
    public Response<RespAuthor> getBookById(@RequestBody ReqAuthor reqAuthor){
        return authorService.getAuthorById(reqAuthor);
    }

    @PostMapping("/AddBook")
    public Response addBook(@RequestBody ReqBook reqBook) {
        return authorService.addBook(reqBook);
    }
    @PostMapping("/UpdateStudent")
    public Response updateBook(@RequestBody ReqBook reqBook){
        return bookService.updateBook(reqBook);
    }
    @PostMapping("/DeleteBook")
    public Response deleteStudent(@RequestBody ReqBook reqBook){
        return authorService.deleteBook(reqBook);
    }

}
