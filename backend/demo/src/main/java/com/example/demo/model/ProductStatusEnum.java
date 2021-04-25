package com.example.demo.model;

public enum ProductStatusEnum {
    CREATED("Создана"),
    DONE("Закрыта"),
    CANCELED("Отменена");

    private String nameRu;

    private ProductStatusEnum(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameRu() {
        return this.nameRu;
    }

    public String toString() {
        return this.nameRu;
    }
}
