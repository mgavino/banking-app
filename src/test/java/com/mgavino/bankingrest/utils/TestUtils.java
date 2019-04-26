package com.mgavino.bankingrest.utils;

import com.mgavino.bankingrest.bank.service.dto.AccountResultDto;

public class TestUtils {

    public static AccountResultDto createBankResultDto(Long id, Double balance) {
        AccountResultDto bankResultDto = new AccountResultDto();
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

    public static AccountEntity createMockBank(Long id, Double balance, Long userId) {
        AccountEntity bankEntity = new AccountEntity();
        bankEntity.setId(id);
        bankEntity.setBalance(balance);
        bankEntity.setNumber("");
        bankEntity.setUserId(userId);
        bankEntity.setCreationDate(new Date());
        bankEntity.setModificationDate(new Date());
        return bankEntity;
    }*/

}
