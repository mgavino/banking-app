package com.mgavino.bankingrest.bank.service.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MovementDto {

    private String concept;

    @NotNull
    @Min(value=0)
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
