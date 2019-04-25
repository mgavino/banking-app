package com.mgavino.bankingrest.bank.service.dto;

import javax.validation.constraints.NotNull;

public class MovementDto {

    private String concept;

    @NotNull
    private Double amount;

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
