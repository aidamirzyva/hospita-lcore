package com.bookstore.util;

import com.bookstore.dto.request.ReqToken;
import com.bookstore.entity.UserToken;
import com.bookstore.enums.EnumAviableStatus;
import com.bookstore.exception.ExceptionConstants;
import com.bookstore.exception.LibraryException;
import com.bookstore.repository.UserRepository;
import com.bookstore.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Utility {

    private final UserRepository userRepository;

    private final UserTokenRepository userTokenRepository;

    public UserToken checkToken(ReqToken reqToken) {
        Long userId = reqToken.getUserId();
        String token = reqToken.getToken();
        if (userId == null || token == null) {
            throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Imvalid request data");
        }
        User user = userRepository.findUserByIdAndActive(userId, EnumAviableStatus.ACTIVE.value);
        if (user == null) {
            throw new LibraryException(ExceptionConstants.USER_NOT_FOUND, "User not found");
        }
        UserToken userToken = userTokenRepository.findUserTokenByUserAndTokenAndActive(user, token, EnumAviableStatus.ACTIVE.value);
        if (userToken == null) {
            throw new LibraryException(ExceptionConstants.INVALID_TOKEN, "Invalid token");
        }
        return userToken;
    }

}
