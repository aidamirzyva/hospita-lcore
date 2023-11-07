package com.bookstore.controller;


import com.bookstore.dto.request.ReqLogin;
import com.bookstore.dto.request.ReqToken;
import com.bookstore.dto.response.RespUser;
import com.bookstore.dto.response.Response;
import com.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/login")
    public Response<RespUser> login(@RequestBody ReqLogin reqLogin) {

        return userService.login(reqLogin);
    }
    @PostMapping("/logout")
    public Response logout(@RequestBody ReqToken reqToken) {
        return userService.logout(reqToken);
    }

}
