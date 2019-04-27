package com.mgavino.bankingrest.bank.repository.model;

import com.mgavino.bankingrest.core.model.AuditableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bac_bank_account")
public class AccountEntity extends AuditableEntity {

    @Column(name="usr_id", nullable = false)
    private Long userId;

    private String name;

    @Column(nullable = false)
    private Double balance;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
