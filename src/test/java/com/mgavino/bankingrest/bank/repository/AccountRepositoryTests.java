package com.mgavino.bankingrest.bank.repository;

import com.mgavino.bankingrest.bank.repository.model.AccountEntity;
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
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void auditing() {

        // insert data
        UserEntity user = userRepository.save(UtilTests.createUserEntity("test18@mgavino.com", "password"));

        // call
        AccountEntity account = repository.save(UtilTests.createAccountEntity(user.getId(), "Account 18", 15.00));

        // check
        Assert.assertNotNull(account);
        Assert.assertNotNull(account.getCreationDate());
        Assert.assertNotNull(account.getModificationDate());
        Assert.assertEquals(account.getCreationDate(), account.getModificationDate());

        // call
        account.setName("Account 19");
        account = repository.save(account);
        Assert.assertNotEquals(account.getCreationDate(), account.getModificationDate());

    }

}
