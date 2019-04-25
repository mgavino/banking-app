package com.mgavino.bankingrest.bank.service.dto;

public class MovementDto {

    private String concept;
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
