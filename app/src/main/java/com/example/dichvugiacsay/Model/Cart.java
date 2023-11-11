package com.example.dichvugiacsay.Model;

import java.io.Serializable;

public class Cart implements Serializable {
    private int idStore, idService, quantity, priceService;
    private String nameService, description;

    public Cart(int idStore, int idService, int quantity, int priceService, String nameService, String description) {
        this.idStore = idStore;
        this.idService = idService;
        this.quantity = quantity;
        this.priceService = priceService;
        this.nameService = nameService;
        this.description = description;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "idStore=" + idStore +
                ", idService=" + idService +
                ", quantity=" + quantity +
                ", priceService=" + priceService +
                ", nameService='" + nameService + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriceService() {
        return priceService;
    }

    public void setPriceService(int priceService) {
        this.priceService = priceService;
    }
}
