package com.mgavino.bankingrest.utils;

import com.mgavino.bankingrest.bank.repository.model.AccountEntity;
import com.mgavino.bankingrest.bank.repository.model.MovementEntity;
import com.mgavino.bankingrest.bank.service.dto.AccountResultDto;
import com.mgavino.bankingrest.bank.service.dto.MovementResultDto;
import com.mgavino.bankingrest.user.repository.model.UserEntity;
import com.mgavino.bankingrest.user.service.dto.UserResultDto;

import java.util.Date;

public class UtilTests {

    public static AccountResultDto createAccountResultDto(Long id, Double balance) {
        AccountResultDto resultDto = new AccountResultDto();
        resultDto.setId(id);
        resultDto.setName("Account " + id);
        resultDto.setBalance(balance);
        return resultDto;
    }

    public static MovementResultDto createMovementResultDto(Long id, Double amount) {
        MovementResultDto resultDto = new MovementResultDto();
        resultDto.setId(id);
        resultDto.setAmount(amount);
        resultDto.setConcept("Concept " + id);
        resultDto.setDate(new Date());
        return resultDto;
    }

    public static UserResultDto createUserResultDto(Long id) {
        UserResultDto resultDto = new UserResultDto();
        resultDto.setId(id);
        resultDto.setEmail("test" + id + "@mgavino.com");
        return resultDto;
    }

    public static AccountEntity createAccountEntity(Long userId, String name, Double balance) {
        AccountEntity entity = new AccountEntity();
        entity.setUserId(userId);
        entity.setName(name);
        entity.setBalance(balance);
        return entity;
    }

    public static UserEntity createUserEntity(String email, String password) {
        UserEntity entity = new UserEntity();
        entity.setEmail(email);
        entity.setPassword(password);
        return entity;
    }

    public static MovementEntity createMovementEntity(Long bankId, String concept, Double amount) {
        return createMovementEntity(bankId, concept, amount, 0);
    }

    public static MovementEntity createMovementEntity(Long bankId, String concept, Double amount, long timespamp) {
        MovementEntity entity = new MovementEntity();
        entity.setBankAccountId(bankId);
        entity.setConcept(concept);
        entity.setAmount(amount);
        if (timespamp > 0) {
            entity.setDate(new Date(timespamp));
        }
        return entity;
    }
}
