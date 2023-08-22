package com.test.service.client;

import com.test.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserFallbackClient implements UserClient{
    @Override
    public User getUserById(int uid) {
        User user = new User();
        user.setName("Fallback User!!");
        return user;
    }
}
