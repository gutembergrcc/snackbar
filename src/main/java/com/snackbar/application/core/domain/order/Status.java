package com.snackbar.application.core.domain.order;

public enum Status {


    OPEN("Aberto"),
    PREPARING("Preparando"),
    DELIVERED("Entregue");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
