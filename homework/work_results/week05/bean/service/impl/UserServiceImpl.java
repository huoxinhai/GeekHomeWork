package com.geek.homework.week05.bean.service.impl;

import com.geek.homework.week05.bean.dao.UserDao;
import com.geek.homework.week05.bean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean login(String userName, String password) {
        System.out.println("service层登录方法=========");
        return userDao.login(userName, password);
    }
}
