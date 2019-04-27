package com.mgavino.bankingrest.bank.repository.model;

import com.mgavino.bankingrest.core.model.AuditableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bac_bank_account")
public class AccountEntity extends AuditableEntity {

    @Column(name="usr_id", nullable = false)
    private Long user;

    private String name;

    @Column(nullable = false)
    private Double balance;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
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
