package github.aaa4.server.accountAndRoleTests;

import github.aaa4.server.entity.Account;
import github.aaa4.server.entity.Role;
import github.aaa4.server.entity.Roles;
import github.aaa4.server.repo.AccountRepository;
import github.aaa4.server.repo.RoleRepository;
import github.aaa4.server.services.AccountAndRoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TestSavingMapped {


    @Autowired
    AccountAndRoleService service;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;


    @Test
    public void testSaving() {
        service.deleteAllRoles();
        service.deleteAllAccounts();
        service.deleteAllRoles();


        Account john = new Account();
        john.setName("john");
        accountRepository.save(john);

        Role user = new Role(Roles.USER);
        roleRepository.save(user);


        john.addNewRole(user);
        accountRepository.save(john);
        Set<Role> jonhsRoles = accountRepository.findById(john.getId()).get().getRoles();


        assertEquals(1, jonhsRoles.size());
        assertEquals(true, jonhsRoles.contains(user));

    }


    @Test
    public void testSavingEqualityWithNew() {
        service.deleteAllRoles();
        service.deleteAllAccounts();
        service.deleteAllRoles();

        Role editor = new Role(Roles.EDITOR);
        roleRepository.save(editor);
        assertEquals(new Role(Roles.EDITOR), roleRepository.findDistinctByRole(Roles.EDITOR).get());
    }

    @Test
    public void deleteSavedUserWithCertainRole() {
        service.deleteAllAccounts();
        service.deleteAllRoles();

        assertEquals(0, accountRepository.count());
        assertEquals(0, roleRepository.count());

        Role user = new Role(Roles.USER);
        Role guest = new Role(Roles.GUEST);

        service.saveRole(user);
        service.saveRole(guest);

        Account ug = new Account();
        ug.setName("userGuest");
        service.saveAccount(ug);


        ug.addNewRole(user);
        ug.addNewRole(guest);

        service.saveAccount(ug);

        assertEquals(1, accountRepository.count());
        assertEquals(2, roleRepository.count());

        assertEquals(ug, accountRepository.findById(ug.getId()).get());
        Set<Role> roles = accountRepository.findById(ug.getId()).get().getRoles();

        assertEquals(true, roles.contains(user));
        assertEquals(true, roles.contains(guest));


        service.deleteAccount(ug);
        assertEquals(0, accountRepository.count());
    }


    @Test
    public void testSaveAccountAndRole() {
        service.deleteAllRoles();
        service.deleteAllAccounts();
        service.deleteAllRoles();

        Account account = new Account();
        account.setName("T5511XJ");
        service.saveAccount(account);

        Role admin = new Role(Roles.ADMIN);
        service.saveRole(admin);


        account.addNewRole(admin);
        service.saveAccount(account);

        Set<Role> roles = accountRepository.findById(account.getId()).get().getRoles();

        assertEquals(1, accountRepository.count());
        assertEquals(account, accountRepository.findById(account.getId()).get());
        assertEquals(admin, roleRepository.findDistinctByRole(Roles.ADMIN).get());

        assertEquals(true, roles.contains(admin));

    }

}
