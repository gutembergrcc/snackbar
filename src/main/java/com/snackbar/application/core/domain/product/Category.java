package com.snackbar.application.core.domain.product;

public enum Category {


    BEVERAGES("Bebidas"),
    DESSERT("Sobremesa"),
    SNACK("Lanche"),
    ACCOMPANIMENT("Acompanhamento");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
