package com.geek.homework.week05.bean.service;

public interface UserService {
    /**
     * 模拟登录，校验用户是否可以登录
     * @param userName
     * @param password
     * @return
     */
    boolean login(String userName, String password);
}
