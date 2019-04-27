package com.mgavino.bankingrest.bank.service.dto;

import javax.validation.constraints.NotNull;

public class AccountDto {

    @NotNull
    private Long user;

    private String name;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
