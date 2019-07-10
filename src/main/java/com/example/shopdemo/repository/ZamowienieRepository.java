package com.example.shopdemo.repository;

import com.example.shopdemo.model.Produkt;
import com.example.shopdemo.model.User;
import com.example.shopdemo.model.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long> {
    List<Zamowienie> findByUser(User user);
}
