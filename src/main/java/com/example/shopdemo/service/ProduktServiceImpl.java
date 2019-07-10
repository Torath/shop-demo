package com.example.shopdemo.service;

import com.example.shopdemo.model.Produkt;
import com.example.shopdemo.repository.ProduktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProduktServiceImpl implements ProduktService{
    @Autowired
    ProduktRepository produktRepository;

    @Override
    public void save(Produkt produkt) {
        produktRepository.save(produkt);
    }

    @Override
    public List<Produkt> findAll() {
        return produktRepository.findAll();
    }

    @Override
    public Produkt findById(Long id) {
        return produktRepository.findById(id).orElse(new Produkt());
    }
}
