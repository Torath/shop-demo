package com.example.shopdemo.service;

import com.example.shopdemo.model.User;
import com.example.shopdemo.model.Zamowienie;
import com.example.shopdemo.repository.ZamowienieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZamowienieServiceImpl implements ZamowienieService {
    @Autowired
    ZamowienieRepository zamowienieRepository;


    @Override
    public void save(Zamowienie zamowienie) {
        zamowienieRepository.save(zamowienie);
    }

    @Override
    public List<Zamowienie> findAll() {
        return zamowienieRepository.findAll();
    }

    @Override
    public List<Zamowienie> findByUser(User user) {
        return zamowienieRepository.findByUser(user);
    }

    @Override
    public Zamowienie findById(Long id) {
        return zamowienieRepository.findById(id).orElse(new Zamowienie());
    }

}
