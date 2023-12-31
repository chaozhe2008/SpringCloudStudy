package com.test.service.impl;

import com.test.entity.User;
import com.test.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements com.test.service.UserService {
    @Resource
    UserMapper mapper;
    @Override
    public User getUserById(int uid) {
        return mapper.getUserById(uid);
    }
}
