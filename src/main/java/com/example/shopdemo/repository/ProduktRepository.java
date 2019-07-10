package com.example.shopdemo.repository;

import com.example.shopdemo.model.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduktRepository extends JpaRepository<Produkt, Long> {

}
