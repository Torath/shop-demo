package com.example.shopdemo.model;

import javax.persistence.*;

@Entity
@Table(name="zamowienie")
public class Zamowienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String adresWysylki;

    @ManyToOne
    @JoinColumn(name = "produkt_id")
    private Produkt produkt;

    @Enumerated(EnumType.STRING)
    private StatusZamowienia statusZamowienia;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAdresWysylki() {
        return adresWysylki;
    }

    public void setAdresWysylki(String adresWysylki) {
        this.adresWysylki = adresWysylki;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public StatusZamowienia getStatusZamowienia() {
        return statusZamowienia;
    }

    public void setStatusZamowienia(StatusZamowienia statusZamowienia) {
        this.statusZamowienia = statusZamowienia;
    }
}
