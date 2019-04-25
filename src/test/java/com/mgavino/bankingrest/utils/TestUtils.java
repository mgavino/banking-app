package com.mgavino.bankingrest.utils;

import com.mgavino.bankingrest.bank.repository.model.BankAccountEntity;
import com.mgavino.bankingrest.bank.service.dto.BankAccountResultDto;
import com.mgavino.bankingrest.user.repository.model.UserEntity;

import java.util.Date;

public class TestUtils {

    public static BankAccountResultDto createBankResultDto(Long id, Double balance) {
        BankAccountResultDto bankResultDto = new BankAccountResultDto();
        bankResultDto.setId(id);
        bankResultDto.setBalance(balance);
        return bankResultDto;
    }

   /* public static UserEntity createMockUser(Long id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setEmail("mgavino@mgavino.com");
        userEntity.setPassword("password");
        userEntity.setCreationDate(new Date());
        userEntity.setModificationDate(new Date());
        return userEntity;
    }

    public static BankAccountEntity createMockBank(Long id, Double balance, Long userId) {
        BankAccountEntity bankEntity = new BankAccountEntity();
        bankEntity.setId(id);
        bankEntity.setBalance(balance);
        bankEntity.setNumber("");
        bankEntity.setUserId(userId);
        bankEntity.setCreationDate(new Date());
        bankEntity.setModificationDate(new Date());
        return bankEntity;
    }*/

}
