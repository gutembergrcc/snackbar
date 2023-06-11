package com.snackbar.application.core.domain.product;

public enum Category {


    BEVERAGES("Bebidas"),
    DESSERT("Sobremesa"),
    SNACK("Lanche"),
    ACCOMPANIMENT("Acompanhamento");

    private String name;

    private Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
