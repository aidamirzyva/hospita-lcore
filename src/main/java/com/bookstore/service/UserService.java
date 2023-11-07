package com.bookstore.service;


import com.bookstore.dto.request.ReqLogin;
import com.bookstore.dto.request.ReqToken;
import com.bookstore.dto.response.RespUser;
import com.bookstore.dto.response.Response;

public interface UserService {
    Response<RespUser> login(ReqLogin reqLogin);

    Response logout(ReqToken reqToken);
}
