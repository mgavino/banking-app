package com.mgavino.bankingrest.user.repository;

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
public class UserRepositoryTests {

    @Autowired
    private UserRepository repository;

    @Test
    public void auditing() {

        // call
        UserEntity user = repository.save(UtilTests.createUserEntity("test20@mgavino.com", "password"));

        // check
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getCreationDate());
        Assert.assertNotNull(user.getModificationDate());
        Assert.assertEquals(user.getCreationDate(), user.getModificationDate());

        // call
        user.setPassword("password2");
        user = repository.save(user);
        Assert.assertNotEquals(user.getCreationDate(), user.getModificationDate());

    }

}
