package com.mgavino.bankingrest.bank.repository.model;

import com.mgavino.bankingrest.core.model.AuditableEntity;
import com.mgavino.bankingrest.core.model.IdentifyEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "bac_bank_account")
public class AccountEntity extends AuditableEntity {

    @Column(name="usr_id")
    private Long userId;

    private String name;

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
