package com.example.shopdemo.service;

import com.example.shopdemo.model.Produkt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface ProduktService {
    void save(Produkt produkt);

    List<Produkt> findAll();

    Produkt findById(Long id);
}
