package com.example.shopdemo.model;


import javax.persistence.*;

@Entity
@Table(name = "produkt")
public class Produkt {
    @Id
    @GeneratedValue()
    private long id;

    private String nazwaProduktu;
    private float cena;
    private int iloscWMagazynie;


    @Enumerated(EnumType.STRING)
    private Kategoria kategoria;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public void setNazwaProduktu(String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public int getIloscWMagazynie() {
        return iloscWMagazynie;
    }

    public void setIloscWMagazynie(int iloscWMagazynie) {
        this.iloscWMagazynie = iloscWMagazynie;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }
}
