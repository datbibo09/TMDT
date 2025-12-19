package com.ecommerce.product.domain.model;

public class ProductEvent {
    public enum EventType { CREATED, UPDATED, DELETED }

    private EventType type;
    private Product product;

    public ProductEvent(EventType type, Product product) {
        this.type = type;
        this.product = product;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

