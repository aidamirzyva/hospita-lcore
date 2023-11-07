package com.bookstore.service;

import com.bookstore.dto.request.ReqAuthor;
import com.bookstore.dto.request.ReqBook;
import com.bookstore.dto.request.ReqToken;
import com.bookstore.dto.response.RespAuthor;
import com.bookstore.dto.response.RespBook;
import com.bookstore.dto.response.Response;

import java.util.List;

public interface AuthorService {

    Response<List<RespBook>> getAuthorList(ReqToken reqToken);
    Response<List<RespAuthor>>getAuthorListBy

    Response<RespBook> getAuthorById(ReqAuthor reqAuthor);

    Response addAuthor(ReqAuthor reqAuthor);

    Response updateAuthor(ReqAuthor reqAuthor);

    Response deleteBook(ReqAuthor reqAuthor);
}
