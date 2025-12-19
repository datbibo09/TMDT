package com.ecommerce.product.domain.model;

import com.ecommerce.product.domain.exception.InvalidProductNameException;
import com.ecommerce.product.domain.exception.InvalidProductPriceException;
import com.ecommerce.product.domain.exception.InvalidProductStockException;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends PanacheEntity {

    private String name;
    private double price;
    private int availableQuantity;

    public void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidProductNameException();
        }
        if (price <= 0) {
            throw new InvalidProductPriceException(price);
        }
        if (availableQuantity < 0) {
            throw new InvalidProductStockException(availableQuantity);
        }
    }

    public void update(String name, double price, int availableQuantity) {
        this.validate();
        this.name = name.trim();
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}