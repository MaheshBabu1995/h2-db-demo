package com.java.h2dbdemo.repo;

import com.java.h2dbdemo.model.User;

public interface UserRepoTwo {
    public int saveAndFlush(User user);
    public User findByUserByName(String name);
}
