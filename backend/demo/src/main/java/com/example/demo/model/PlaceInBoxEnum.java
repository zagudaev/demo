package com.example.demo.model;

public enum PlaceInBoxEnum {
    ONE("первое"),
    TWO("второе"),
    THREE("третье");

    private String nameRu;

    private PlaceInBoxEnum(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameRu() {
        return this.nameRu;
    }

    public String toString() {
        return this.nameRu;
    }
}
