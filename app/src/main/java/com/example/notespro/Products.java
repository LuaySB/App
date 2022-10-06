package com.example.notespro;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Products {

    String offer, store, price, pre, kategori;

    public Products(){}


    public Products(String offer, String store, String price, String pre, String kategori) {
        this.offer = offer;
        this.store = store;
        this.price = price;
        this.pre = pre;
        this.kategori = kategori;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
