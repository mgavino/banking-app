package com.mgavino.bankingrest.user.repository.model;

import com.mgavino.bankingrest.core.model.AuditableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usr_user")
public class UserEntity extends AuditableEntity {

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
