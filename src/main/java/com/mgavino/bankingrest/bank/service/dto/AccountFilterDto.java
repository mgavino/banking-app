package com.mgavino.bankingrest.bank.service.dto;

import javax.validation.constraints.NotNull;

public class AccountFilterDto {

    @NotNull
    private Long user;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
