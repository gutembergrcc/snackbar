package com.snackbar.adapters.inbound.rest.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

public class AutenticateRequest {

    @JsonProperty("cpf")
    @NotBlank
    @CPF
    @Schema(description = "CPF do cliente somente números sem ponto (.) e traço (-)", example = "03727327818")
    private String cpfNumber;

    public String getCpfNumber() {
        return cpfNumber;
    }

    public void setCpfNumber(String cpfNumber) {
        this.cpfNumber = cpfNumber;
    }

}
