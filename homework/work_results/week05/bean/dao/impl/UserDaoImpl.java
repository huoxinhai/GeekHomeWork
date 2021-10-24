package com.geek.homework.week05.bean.dao.impl;

import com.geek.homework.week05.bean.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public boolean login(String username, String password) {
        return "admin".equals(username) && "admin".equals(password);
    }
}
