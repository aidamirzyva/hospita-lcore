package com.bookstore.repository;

import com.bookstore.entity.UserToken;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    UserToken findUserTokenByUserAndTokenAndActive(User user, String token, Integer active);
    UserToken findUserTokenByUserIdAndTokenAndActive(Long userId, String token, Integer active);

}
