package com.snackbar.application.core.domain.order;

public enum OrderStatus {

    RECEIVED("Recebido"),
    IN_PREPARATION("Em preparação"),
    READY("Pronto"),
    FINISHED("Finalizado");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
