package com.csl.demo.service.impl;

import com.csl.demo.entity.User;
import com.csl.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author RC
 * @description 用户业务实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String addUser(User user) {
        // 直接编写业务逻辑
        return "success";
    }
}
