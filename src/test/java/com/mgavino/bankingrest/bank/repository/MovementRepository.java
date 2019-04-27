package com.mgavino.bankingrest.bank.repository;

import com.mgavino.bankingrest.bank.repository.model.AccountEntity;
import com.mgavino.bankingrest.bank.repository.model.MovementEntity;
import com.mgavino.bankingrest.user.repository.UserRepository;
import com.mgavino.bankingrest.user.repository.model.UserEntity;
import com.mgavino.bankingrest.utils.UtilTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovementRepository {

    @Autowired
    private MovementRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void auditing() {

        // insert data
        UserEntity user = userRepository.save(UtilTests.createUserEntity("test19@mgavino.com", "password"));
        AccountEntity account = accountRepository.save(UtilTests.createAccountEntity(user.getId(), "Account 19", 10.0));

        // call
        MovementEntity movement = repository.save(UtilTests.createMovementEntity(account.getId(), "Concept", 10.00));

        // check
        Assert.assertNotNull(movement);
        Assert.assertNotNull(movement.getDate());

    }

}
