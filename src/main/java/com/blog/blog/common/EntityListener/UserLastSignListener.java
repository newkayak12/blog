package com.blog.blog.common.EntityListener;

import com.blog.blog.repository.entity.User;
import com.blog.blog.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostLoad;
import javax.transaction.Transactional;
import java.util.Date;

public class UserLastSignListener  {
    @Autowired
    private UserRepository userRepository;

    @PostLoad
    public void postLoad(User entity){
        entity.setUserLastSignedDate(new Date());
        userRepository.save(entity);
    }
}
