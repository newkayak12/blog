package com.blog.blog.repository.userRepository;

import com.blog.blog.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByUserIdOrUserNickname(String userId, String userNickname);
    public User findUserByUserId(String UserId);
}
