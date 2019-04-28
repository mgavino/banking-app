package com.mgavino.bankingrest.bank.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MovementResultDto {

    private Long id;
    private String concept;
    private Double amount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
