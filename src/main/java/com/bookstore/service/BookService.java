package com.bookstore.service;

import com.bookstore.dto.request.ReqBook;
import com.bookstore.dto.request.ReqToken;
import com.bookstore.dto.response.RespBook;
import com.bookstore.dto.response.Response;

import java.util.List;

public interface BookService {

    Response<List<RespBook>> getBookList(ReqToken reqToken);

    Response<RespBook> getBookById(ReqBook reqBook);

    Response addBook(ReqBook reqBook);

    Response updateBook(ReqBook reqBook);

    Response deleteBook(ReqBook reqBook);
}
