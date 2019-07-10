package com.example.shopdemo.service;

import com.example.shopdemo.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
