package com.mgavino.bankingrest.bank.model;

import com.mgavino.bankingrest.core.model.IdentifyEntity;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "bmv_bank_movement")
public class MovementEntity extends IdentifyEntity {

    @Column(name="bac_id")
    private Long bankAccountId;

    private String concept;

    private Double amount;

    @CreatedDate
    private Date date;

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
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
