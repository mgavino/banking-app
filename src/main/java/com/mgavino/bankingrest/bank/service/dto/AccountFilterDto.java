package com.mgavino.bankingrest.bank.service.dto;

import javax.validation.constraints.NotNull;

public class AccountFilterDto {

    @NotNull
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
