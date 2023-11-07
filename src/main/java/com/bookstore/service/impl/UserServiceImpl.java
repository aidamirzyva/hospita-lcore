package com.bookstore.service.impl;

import com.bookstore.dto.request.ReqLogin;
import com.bookstore.dto.request.ReqToken;
import com.bookstore.dto.response.RespToken;
import com.bookstore.dto.response.RespUser;
import com.bookstore.dto.response.Response;
import com.bookstore.entity.UserToken;
import com.bookstore.enums.EnumAviableStatus;
import com.bookstore.exception.ExceptionConstants;
import com.bookstore.exception.LibraryException;
import com.bookstore.repository.UserRepository;
import com.bookstore.repository.UserTokenRepository;
import com.bookstore.service.UserService;
import com.bookstore.util.Utility;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.aspectj.weaver.EnumAnnotationValue;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final Utility utility;
    private final UserRepository userRepository;
    private final UserTokenRepository;

    @Override
    public Response<RespUser> login(ReqLogin reqLogin) {
        Response<RespUser> response = new Response<>();
        RespUser respUser = new RespUser();
        try {
            String username = reqLogin.getUsername();
            String password = reqLogin.getPassword();
            if (username == null || password == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            User user = userRepository.findUserByUsernameAndPasswordAndActive(username, password, EnumAviableStatus.ACTIVE.value);
             if( user == null){
                 throw new LibraryException(ExceptionConstants.USER_NOT_FOUND, "User not found");
             }
             String token = UUID.randomUUID().toString();
             UserToken userToken = UserToken.builder()
                     .user(user)
                     .token(token)
                     .build();
             UserTokenRepository.save(userToken);
             respUser.setUsername(username);
             respUser.setFullName(user.getFullName());
             respUser.setRespToken(new RespToken(user.getId(),));
        }

        return response;


    }

    @Override
    public Response logout(ReqToken reqToken) {
        return null;
    }
}