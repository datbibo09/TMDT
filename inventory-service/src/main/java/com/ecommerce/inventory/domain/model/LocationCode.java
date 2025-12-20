package com.ecommerce.inventory.domain.model;

public enum LocationCode {
    HN("Kho Hà Nội"),
    HCM("Kho TP.HCM"),
    DN("Kho Đà Nẵng"),
    HP("Kho Hải Phòng"),
    CT("Kho Cần Thơ");

    private final String displayName;

    LocationCode(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}