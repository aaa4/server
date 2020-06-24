package github.aaa4.server.accountAndRoleTests;

import github.aaa4.server.entity.Account;
import github.aaa4.server.entity.Role;
import github.aaa4.server.entity.Roles;
import github.aaa4.server.repo.AccountRepository;
import github.aaa4.server.services.AccountAndRoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
@Profile("test")
public class TestDelition {


    @Autowired
    private AccountAndRoleService service;
    @Autowired
    private AccountRepository repository;

    // @Test
    public void testEquality() {
        Account a = new Account();
        Account b = new Account();
        UUID sameId = UUID.randomUUID();
        String sameName = "Johansen";

        a.setId(sameId);
        b.setId(sameId);

        a.setName(sameName);
        b.setName(sameName);

        assertEquals(a, b);
    }


    Account a = new Account();
    Account b = new Account();

    @Test
    public void deleteAll() {

        repository.save(a);
        Role guest = new Role(Roles.GUEST);
        b.addNewRole(guest);
        repository.save(b);
        service.deleteAllAccounts();
        assertEquals(0, repository.count());


    }


}
