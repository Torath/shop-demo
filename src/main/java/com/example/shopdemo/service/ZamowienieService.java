package com.example.shopdemo.service;

import com.example.shopdemo.model.User;
import com.example.shopdemo.model.Zamowienie;

import java.util.List;

public interface ZamowienieService {
    void save(Zamowienie zamowienie);

    List<Zamowienie> findAll();

    List<Zamowienie> findByUser(User user);

    Zamowienie findById(Long id);
}
