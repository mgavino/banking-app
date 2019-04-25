package com.mgavino.bankingrest.bank.service.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MovementFilterDto {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date from;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date to;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
